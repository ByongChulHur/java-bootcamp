# Lab 20 — Clear MDC Finally Drill

## Bug story
Request A (Ravi, CUS-1002) sets correlationId=lab-request-001 via MDC.put,
but never clears it. The thread returns to the pool still holding that value.
Request B (Amina, CUS-1001) reuses the same thread and its early log lines
get stamped with Request A's correlation ID instead of its own — a
"wrong customer" mix-up in the logs.

## Fix
Wrap MDC.put in try/finally in the filter:
try {
MDC.put("correlationId", correlationId);
filterChain.doFilter(request, response);
} finally {
MDC.clear();
}

## Test idea
After a request completes, assert MDC.get("correlationId") is null.
If clear() is ever skipped, this test fails immediately.

## Scope
Pre-lab only.