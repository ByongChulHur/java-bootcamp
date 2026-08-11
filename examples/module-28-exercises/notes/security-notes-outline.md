# Lab 28 — Production IdP Checklist

## IdP note

Lab 28's in-memory agent1/admin1 users are teaching-mode only. Production should use an external IdP (e.g., Okta, Keycloak, company SSO/OAuth2) so onboarding and offboarding don't require code changes and redeployment.

## Key rotation

Signing keys must be stored in a secret manager (Vault, AWS Secrets Manager), never in code or config files. Rotate on a fixed schedule and immediately after any suspected leak — a stolen signing key lets an attacker forge valid tokens for any role (e.g., ADMIN) without ever logging in.

## Transport / TTL

Use short-lived access tokens (e.g., 15-60 min) paired with a refresh-token flow, and enforce HTTPS everywhere. A short TTL limits the damage window if a token is ever stolen — an intercepted token becomes useless soon after capture instead of staying valid indefinitely.

## Logging hygiene

Audit and log security events (login success/failure, 401/403 occurrences) for monitoring, but never log raw bearer tokens or passwords — logs are themselves a leak vector.

## Scope

Pre-lab only. No real secrets.