# Lab 16 — error model notes

| Status | Code | When |
| ------ | ---- | ---- |
| 400 | VALIDATION_FAILED | Bean Validation on request DTO |
| 404 | CUSTOMER_NOT_FOUND | Unknown customer id |
| 409 | BUSINESS_CONFLICT | Illegal status transition / duplicate |

Correlation id: `lab-request-001`

## Failure Experiments (verified)

1. Bare RuntimeException-style failure during create() → generic 500,
   no internal details (e.g. status enum error) leaked into client JSON.
2. Two invalid fields (blank fullName + bad email) → both appear in
   errors map. LinkedHashMap aggregation confirmed working.
4. Swapping catch order (Exception before BusinessException) →
   compiler rejects it: "exception BusinessException has already been caught."
   Confirms catch order matters and is enforced by the compiler itself.
