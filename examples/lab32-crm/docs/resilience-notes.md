# Lab 32 — Resilience notes

## Instance name

All three annotations (`@CircuitBreaker`, `@Retry`, `@TimeLimiter`) on `AccountProfileService.find()` use the same instance name, `accountProfile`. This name links each annotation to the matching configuration block in `application.yml` (`resilience4j.circuitbreaker.instances.accountProfile`, `resilience4j.retry.instances.accountProfile`, `resilience4j.timelimiter.instances.accountProfile`). If the names didn't match exactly, Resilience4j would silently fall back to default settings instead of our tuned values (sliding window 6, min calls 4, failure threshold 50%, 2s open wait, 3 retry attempts, 1500ms timeout) — the annotation and the YAML config would be disconnected with no compile-time error to warn us.

## Truthful fallback

When the Account Profile dependency fails (timeout, 503, or the circuit is OPEN), the fallback method returns `AccountSummary.unavailable(customerId)`, which sets `available=false` and a fixed note (`"account-profile-unavailable"`) — never a fabricated balance, tier, or other account detail. This matters because a fallback that returns `available=true` with empty or guessed data would be indistinguishable from a genuinely successful, funded account response. The React UI depends on the `available` flag to decide whether to show real account data or a "temporarily unavailable" banner; if the fallback silently claimed success, an agent could believe a customer's account information is current when it's actually degraded or missing, which is a directly misleading UX and, for write operations, an integrity risk (a customer might be told an update succeeded when it did not).

## Evidence from this run

- Verified via `mvn test -Dtest=AccountProfileResilienceTest`, run twice consecutively — `Tests run: 3, Failures: 0, Errors: 0` both times.
- Log evidence of each pattern firing on `CUS-1001`:
    - `TimeoutException` → TimeLimiter cut off a 3000ms delayed stub before the 1500ms budget was exceeded.
    - `TemporaryAccountException` (×4) → Retry attempted the 503 stub multiple times before CircuitBreaker recorded enough failures.
    - `CallNotPermittedException` → CircuitBreaker was OPEN and rejected the call without hitting WireMock.

## Failure Experiments — sanity checks (not executed as code)

### Experiment 4 — Retrying a non-idempotent write
We do not apply `@Retry` to write operations (e.g., account updates). If a POST/PUT request actually succeeds on the server but the response is lost in transit (network blip after the write completed), retrying it would resend the same write — for a non-idempotent operation, this risks applying the change twice (e.g., double-charging or duplicate record creation). Our `@Retry(name = "accountProfile")` is scoped only to `AccountProfileService.find()`, which is a GET (read-only, safe to repeat). This is documented here rather than demonstrated in code because deliberately building an unsafe retry-on-write path would contradict the lab's safety goal.

### Experiment 5 — Fallback claiming false success
Our fallback (`AccountSummary.unavailable`) always sets `available=false`. We verified this is impossible to bypass by inspection: the fallback method has a single return statement and no branch that could accidentally set `available=true`. If a future change to this method returned a "shaped like success" `AccountSummary` (available=true with empty/default fields) while the dependency was actually down, that would be a lie to the UI and to the agent viewing the page — exactly the kind of degraded-response dishonesty this lab is designed to prevent.