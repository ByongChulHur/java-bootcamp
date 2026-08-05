# Lab 21 — Liveness vs Readiness

## Liveness
Liveness answers "is the process itself alive, or stuck/unrecoverable?" 
The following should only fail when the process cannot recover on its own.
It is the process that restarts if there are problems.

## Readiness
Readiness answers "can this instance serve traffic right now?" It fails when
a dependency the app needs is temporarily unavailable — for example, the
database connection is down. When readiness fails, Kubernetes stops routing
traffic to this pod and do not restart itself.

## Wrong mix
If it mix up the two, then it could get in loop. For example, if readiness fails because the database is down, but if the kubernetes restarts the pod
then since it will be failing unti the database is back up, it will keep restarting and never recover.

## Lab expectation
toggle CrmReadinessIndicator OUT_OF_SERVICE; liveness stays UP.

## Scope
Pre-lab only.