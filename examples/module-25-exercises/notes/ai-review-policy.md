# Lab 25 — AI Review Policy

## Must reject
- Service returning ResponseEntity — Service must not know about HTTP types.
- Controller importing Repository types — a shortcut that skips the layer boundary.
- JPA introduced ahead of scope — this lab stays in-memory only (JPA comes in Lab 39).
- Hardcoded production passwords or secrets.
- PII or sensitive data logged in plain text.

## Must check
- Fixtures are correct: CUS-1001 (Amina Khan, ACTIVE), CUS-1002 (Ravi Singh, PROSPECT).
- Repository is passed to Service via constructor injection, not field injection or `new`.
- Tests verify real behavior (duplicate rejection, not-found handling), not just `assertNotNull`.
- Controller stays thin — no business logic.
- Duplicate check happens before save, not after.

## Where to record review
All AI review notes go in `docs/lab25-001.md` with an accept/reject call and a short reason for each; if AI wasn't used, write "N/A — no AI used, layering done manually" there instead.

## Scope
Pre-lab only.