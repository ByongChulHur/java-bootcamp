# Lab 32 — Why Resilience

## Step 1 — Scenario
Customer detail for `CUS-1001` Amina calls Account Profile. The dependency hangs 30s. List three user-visible or thread-pool effects.

1. The customer detail page for CUS-1001 (Amina Khan) appears frozen and keeps spinning for the full 30 seconds, since the CRM is waiting on the Account Profile response before it can render anything.
2. The request-handling thread is blocked for the entire 30 seconds and cannot serve any other customer during that time. If several agents open account details around the same time, the thread pool can fill up and unrelated requests start queuing or timing out too.
3. Health checks / liveness probes on the CRM service may start failing or reporting degraded status because response latency spikes sharply, which could trigger unnecessary restarts or removal from the load balancer.

## Step 2 — Pattern names
Write the four Resilience4j ideas: retry, circuit breaker, time limiter, fallback.

- Retry
- Circuit Breaker
- Time Limiter
- Fallback

## Step 3 — Not a substitute
One sentence: resilience wraps calls; it does not fix a permanently wrong URL.

Resilience patterns like Retry, Circuit Breaker, Time Limiter, and Fallback protect the caller from transient or temporary failures, but they cannot fix a permanently wrong URL, invalid credentials, or broken configuration — that root cause still has to be fixed manually.

## Step 4 — Notes file
This file: `notes/lab32-resilience.md`

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.