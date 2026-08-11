# Lab 28 — Security notes

## 401 vs 403
- 401 Unauthorized: caller is not authenticated (missing/invalid/expired JWT).
- 403 Forbidden: caller is authenticated but lacks the required role (e.g. AGENT hitting /api/admin/**).

## Local secret vs production
- This lab uses a single shared HS256 secret from `JWT_SECRET` (in-memory `agent1`/`admin1` users, BCrypt-encoded).
- Production must replace this with an external IdP (OAuth2/OIDC), RSA/ECDSA signing keys stored in a secret manager, and scheduled/incident-triggered key rotation. Never commit real secrets — `.env.example` holds a placeholder only.

## Matcher table
| Route | Rule |
| --- | --- |
| /api/auth/login | permitAll |
| /error | permitAll |
| /api/customers/** | hasAnyRole(AGENT, ADMIN) |
| /api/admin/** | hasRole(ADMIN) |
| anyRequest | authenticated |

## Fixtures
- CUS-1001 / CUS-1002; correlation lab-request-001

## Run instructions
```bash
$env:JWT_SECRET='lab-only-...'   # never commit the real value
mvn -q spring-boot:run
# login → capture token (redact in notes) → GET CUS-1001 / admin matrix
mvn -q test
```

## Failure Experiments

| # | Experiment | Result |
| - | ---------- | ------ |
| 1 | No token on GET /api/customers/CUS-1001 | 401 |
| 2 | Wrong password on login | 401, no field-specific leak |
| 3 | agent1 on /api/admin/ping | 403 |
| 4 | Expired token (2s TTL test) reused after expiry | 401 |