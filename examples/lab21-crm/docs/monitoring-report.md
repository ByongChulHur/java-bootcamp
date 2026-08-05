# Lab 21 — monitoring report

## Probes

| Probe | Expected when ready | Expected when lab toggle down |
| ----- | ------------------- | ----------------------------- |
| liveness | UP | UP |
| readiness | UP | OUT_OF_SERVICE / DOWN |

## Metrics

- `crm.customer.create` tag `result`
- `crm.customer.get` tag `result`
- Never tag `customerId` or correlation id

## Production note

Lab exposure of health+metrics+info is **not** production-safe — restrict endpoints later.

## Liveness vs Readiness notes

I personally think live-but-not-ready app is when process still running but
not ready yet, for example schema migration still going on startup. In this
case liveness stay UP and readiness should fail, so kubernetes just stop
routing traffic and don't restart the pod. Dead process is different because
process itself stuck or deadlocked and never move forward, and this is when
liveness should fail and kubernetes need to restart it.

## Example traffic
POST CUS-2101 (Metric User), GET CUS-1001 (Amina Khan), corr=lab-request-001

## Known limitation
I found this during failure experiment. POST with missing fullName return
400 from controller before it even reach CustomerService, so
crm.customer.create{result=failure} never increment. Because of this, right
now metric only catch failure inside service layer, not controller
validation reject. This is a gap I should fix later.

## Alert idea
Alert: CRMCreateFailureRatioHigh
Expr: rate(crm_customer_create_failure[5m]) / rate(crm_customer_create_total[5m]) > 0.05
For: 5m
Action: page on-call, check logs by correlation id

## Production
Lab exposure (health+metrics+info open) is not safe for production.
For last, production need auth and network restriction on actuator
endpoint, and never expose /actuator/env or /actuator/beans.

## Test evidence
ActuatorIT: Tests run 3, Failures 0, Errors 0, BUILD SUCCESS