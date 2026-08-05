# Lab 20 — Fill Forbidden PII Checklist TODOs

Forbidden: email
Forbidden: phone
Forbidden: national id/ Card PAN ideas
Allowed customerId: CUS-1001, CUS-1002
Allowed correlation: lab-request-001
Clear MDC in finally? yes

## Finally snippet
try {
MDC.put("correlationId", correlationId);
filterChain.doFilter(request, response);
} finally {
MDC.clear();
}

## Scope
Pre-lab only.