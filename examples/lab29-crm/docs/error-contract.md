# Northstar CRM — Error Contract

## Status codes

| Case | HTTP Status |
| --- | --- |
| Bad input (validation fails) | 400 |
| Customer not found | 404 |
| Duplicate customer | 409 |
| Unexpected error | 500 |

## What every error looks like

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "correlationId": "lab-request-001",
  "violations": [
    {"field": "email", "message": "must be a valid email"}
  ]
}
```

For 404/409/500, `violations` is just empty `[]`. Only bad input gives violations.

## Security

All `/api/customers/**` need a Bearer token. Login first with
`POST /api/auth/login`. No token → 401.

## Correlation ID

Client sends `X-Correlation-Id` header. Server echoes it back in every
error. If client sends none, server uses `lab-request-001` as default.
This helps find the right log later.

## Tested cases (curl)

| Test | What we sent | Result |
| --- | --- | --- |
| Bad input | blank id/name, bad email | 400 + 3 violations |
| Not found | GET CUS-9999 | 404 |
| Duplicate | POST id CUS-1001 (already exists) | 409 |
| Broken JSON | malformed body | 500, no stack trace |

## Why this matters (Lab 14/16 connection)

Old idea from Lab 14: put validation rules on the DTO.
Old idea from Lab 16: handle exceptions in one central place.

Lab 29 combines both into one working system:
- `CustomerRequest` holds the validation rules (Lab 14 idea).
- `GlobalExceptionHandler` catches every error in one place (Lab 16 idea).

Now every client (React app, curl, test) gets the same error shape,
no matter which layer failed.

## Failure experiments (Lab 29 Step 9)

| # | Experiment | What happened | Restored? |
| - | --- | --- | --- |
| 1 | Sent blank id/name + bad email | 400 with 3 violations (id, name, email) | Kept constraints |
| 2 | GET unknown CUS-9999 | 404 "Customer not found: CUS-9999" | Kept mapping |
| 3 | POST duplicate CUS-1001 | 409 "Duplicate customer: CUS-1001" | Kept mapping |
| 4 | GET customer with no Bearer token | 401 (confirmed via `securityStillRequiresToken` test) | Kept security |
| 5 | Sent malformed JSON body | 500, message "Unexpected error", no stack trace in body | Kept fallback |