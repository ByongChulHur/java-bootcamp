# Lab 32 — Pattern Map

## Reference
| Pattern | CRM use |
| --- | --- |
| Retry | Transient 503 from Account Profile |
| TimeLimiter | Fail fast if call exceeds N ms |
| CircuitBreaker | Stop calling when failure rate high |
| Fallback | Return cached/minimal profile for Amina |

## Step 2 — Add Ravi row
| Pattern | CRM use |
| --- | --- |
| Fallback (open circuit) | For `CUS-1002` Ravi Singh, while the circuit is OPEN the CRM returns `AccountSummary.unavailable("CUS-1002")` immediately, without calling Account Profile at all, showing his cached name/status but marking balance and tier as unavailable. |

## Step 3 — Order idea
TimeLimiter → CircuitBreaker → Retry → call

(TimeLimiter is outermost so it can cancel a hanging attempt even mid-retry; CircuitBreaker sits next so it can short-circuit before any retry is attempted once the breaker is open; Retry is innermost, closest to the actual call, since it only makes sense to retry the real request itself.)

## Step 4 — Boundary
Do not apply a circuit breaker (or retry/timeout) to local in-memory map lookups — these patterns exist to protect against network/remote-dependency failures, and a local map lookup cannot fail in a transient, recoverable way the same way a remote HTTP call can. Applying resilience patterns there only adds overhead with no benefit.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.