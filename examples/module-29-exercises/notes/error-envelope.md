# Lab 29 — ErrorResponse Envelope
## Fields
status, code, message, correlationId, violations

## Violation item shape

Each item in the `violations` array has 3 keys:
- field: the name of the field that failed (e.g. "email")
- rejectedValue: the actual value the client submitted (e.g. "invalid-email")
- message: human-readable reason why it failed (e.g. "must be a valid email address")

## Correlation rule

The server never generates its own correlationId. It reads whatever ID
the client sent (e.g. via an `X-Correlation-Id: lab-request-001` header)
and echoes that same value back in the `correlationId` field of the error
response. This lets the client match a specific request to the exact log
entry that failed. If the client sends no correlation ID, this field may
be omitted or generated server-side as a fallback.
_____
## Scope
Pre-lab only.