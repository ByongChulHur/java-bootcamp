# Lab 20 — Rewrite Unsafe Logs

## Unsafe example
log.info("Customer created: {}", customer);
This is one of the examle that is unsafe because it logs the entire customer object, which may contain sensitive information such as email, phone number, or address. 
Logging sensitive data can lead to security vulnerabilities and privacy issues.

## Safe rewrite (Amina/CUS-1001)
log.info("customerId={} status={} correlation={}", "CUS-1001", "ACTIVE", "lab-request-001");

## Safe Ravi activate start
log.info("customerId={} status={} correlation={}", "CUS-1002", "PROSPECT", "lab-request-001");

## Scope
Pre-lab only.