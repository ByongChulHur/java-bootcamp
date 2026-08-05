## Reflection Questions

1. I personally think readiness group decision affected correctness most, because without adding crmReadinessIndicator into the readiness group 
it would not actually flip readiness independent from liveness.

2. The crm.customer.create metric showing result=success with count=1 after POST CUS-2101, and ActuatorIT's createMetricAppearsAfterTraffic test passing,
proves the create traffic is observable.

3. The hardest failure to diagnose was the controller-level 400 for missing fullName, because it silently skipped CustomerService and never
incremented the failure counter, so I only found it by comparing metric before and after.