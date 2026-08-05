# Lab 20 — MDC Lifecycle

## Put
call MDC.put("correlationId", "lab-request-001").
If the X-Correlation-Id header is already present, reuse that value; otherwise generate a new one before putting it.

## Use
## Use
After that, every log.info/log.error call in the service layer on the same thread automatically includes correlationId

## Clear
When request processing finishes (whether it succeeds or throws), always call MDC.clear()
in a finally block:
try { ... } finally { MDC.clear(); }

## Lab 21 boundary
This exercise only covers application-level correlation IDs and the MDC lifecycle.


## Scope
Pre-lab only.