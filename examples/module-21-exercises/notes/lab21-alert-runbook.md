# Lab 21 — Alert from create_failure_total

## Signal
create_failure_total rate exceeds threshold for N minutes (rate, not raw total)

## Triage steps
1. Check /actuator/health (readiness) first — is a dependency down?
2. If health is UP, search logs by correlation id (e.g. lab-request-001)
   to find the failing customer/request and exact error

## CRM check
Confirm recent create traffic isn't a bad deploy (e.g. new validation rule
rejecting valid PROSPECT payloads) rather than a real dependency issue

## Owner
On-call backend / platform team

## Scope
Pre-lab only.