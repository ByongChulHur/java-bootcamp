# Lab 20 — logging contract

## MDC keys

| Key | Meaning |
| --- | ------- |
| corr | X-Correlation-Id |
| cust | customerId |
| op | create / get |

## Rules

- Never log fullName or email
- Always `MDC.clear()` in filter `finally`

## Logging contract

- Required MDC: correlationId, customerId (when known), op
- Allowed: customerId, status, reason codes, durations, HTTP status
- Forbidden: fullName, email, phone, address, passwords, tokens, PAN
- Correlation header: X-Correlation-Id (example lab-request-001)
- Levels: INFO success path; WARN business reject; ERROR unexpected
- Production: ship to central store; never embed secrets in patterns

## Rules

- Never log any personal data such as fullName, email, phone, address, passwords, tokens, PAN.
- Always `MDC.clear()` in filter `finally`

## Sample INFO/WARN lines

2026-08-04 13:12:27,556 INFO  [http-nio-8080-exec-1] c.n.crm.service.CustomerService corr=lab-request-001 cust=CUS-1001 op=get - Customer lookup found=true
2026-08-04 13:12:31,047 INFO  [http-nio-8080-exec-2] c.n.crm.service.CustomerService corr=lab-request-001 cust=CUS-1002 op=get - Customer lookup found=true
2026-08-04 13:12:34,615 WARN  [http-nio-8080-exec-3] c.n.crm.api.CustomerController corr=lab-request-001 cust= op= - Rejecting create reason=missing_full_name customerId=CUS-1003

## Failure Experiments

2. POST with missing fullName → verified: WARN reason=missing_full_name, no PII, correlation present.
3. Repeat create CUS-1001 → verified: WARN reason=duplicate logged, then exception propagates to controller (500). Correlation ID stayed consistent.
5. Omit MDC.clear() temporarily → attempted, but leak did not reproduce this run because Tomcat assigned a different thread (exec-2) to the second 
request instead of reusing exec-1. Confirms leak only happens when the same thread is reused before