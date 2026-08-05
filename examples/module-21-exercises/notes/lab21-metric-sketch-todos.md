# Lab 21 — Fill Metric Sketch TODOs

Success counter: create_success_total
Failure counter: create_failure_total
Forbidden label: customerId — unbounded values, causes cardinality explosion
Alert name: CrmCreateFailuresHigh
Alert threshold idea: rate(create_failure_total[5m]) > threshold for 5m
First responder action: check /actuator/health, then search logs by
correlation id (lab-request-001) to find the failing customer

## Scope
Pre-lab only.