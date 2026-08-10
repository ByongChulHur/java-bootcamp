# Lab 27 — ACID evidence

| Property | Lab evidence |
| -------- | ------------ |
| Atomicity | POST to ACC-FORCE-FAIL returned HTTP 500 (IllegalStateException after debit was already saved); MAIN balance stayed at 950.00, no matching credit or success log row was written — debit+credit+log succeed or fail together |
| Consistency | After the happy-path transfer (MAIN 1000.00 → 950.00, LOYALTY 50.00 → 100.00), the combined total across both accounts stayed 1050.00 — no negative balance was ever produced |
| Isolation | This lab uses Spring Boot's default isolation level (no custom @Transactional(isolation=...)); only one transfer runs at a time in this lab, so no dirty-read demo was performed — concurrent behavior would need a separate multi-threaded test |
| Durability | The committed MAIN→LOYALTY transfer (950.00 / 100.00) persists for the life of the running app; H2 here is `jdbc:h2:mem:lab27`, an in-memory database, so data does not survive a full app restart — real durability would require a file-based or production DB |


## Failure Experiments

| # | Experiment | Observed result |
| - | ---------- | ---------------- |
| 1 | Transfer to `ACC-FORCE-FAIL` | HTTP 500 returned; `IllegalStateException("Forced transfer failure for rollback demo")` thrown after debit was already applied in-memory. MAIN balance remained unchanged (confirmed by `forceFailRollsBack` automated test). No success row was written to `TransactionLog`. |
| 2 | Repeat the same transfer request twice with no idempotency key (MAIN → LOYALTY, $5.00) | Both requests returned `{"status":"OK"}` (HTTP 200). MAIN was debited $5.00 twice ($10.00 total) because the current design has no duplicate-request protection. This is expected given the lab's scope — idempotency handling is out of scope for Lab 27. |
| 3 | Self-invocation (`this.transfer(...)` called from another method in the same class) | Not reproduced in code, but based on Spring's AOP proxy behavior, the internal call would bypass the proxy and `@Transactional` would be silently ignored — no transaction, no rollback on failure. |

**Conclusion:** `@Transactional` correctly enforces atomicity for a single, externally-invoked call (debit + credit + log succeed or fail together). Known gaps outside this lab's scope: idempotency (duplicate requests) and self-invocation risk if the service is refactored later.