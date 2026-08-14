# Lab 32 — Fill Resilience TODOs

## Step 1 — Paste
Create `notes/lab32-todos.md`:

```java
@CircuitBreaker(name = "accountProfile", fallbackMethod = "profileFallback")
@Retry(name = "accountProfile")
@TimeLimiter(name = "accountProfile")
public CompletableFuture<AccountProfile> getProfile(String customerId) {
    return accountClient.fetch(customerId); // remote client
}

private CompletableFuture<AccountProfile> profileFallback(String customerId, Throwable t) {
    // TODO: return minimal profile for CUS-1001 / CUS-1002
    return CompletableFuture.completedFuture(AccountProfile.minimal(customerId));
}
```

## Step 2 — Fill
- CircuitBreaker name: `accountProfile` — matches the Retry/TimeLimiter instance name so all three share the same YAML config
- fallbackMethod: `profileFallback` — must exactly match the method name below it
- Remote client variable: `accountClient` — the injected client that makes the actual outbound HTTP call
- Fallback return value: `AccountProfile.minimal(customerId)` — an honest, minimal object containing only the customerId, never fabricated balance/tier data

## Step 3 — Config blanks
```yaml
resilience4j:
  circuitbreaker:
    instances:
      accountProfile:
        failure-rate-threshold: 50
        wait-duration-in-open-state: 10s
  retry:
    instances:
      accountProfile:
        max-attempts: 3
```

## Step 4 — Correlation
```java
private CompletableFuture<AccountProfile> profileFallback(String customerId, Throwable t) {
    // TODO: log correlationId "lab-request-001" here when the fallback fires, e.g.
    // log.warn("account_profile_fallback correlationId=lab-request-001 customerId={}", customerId, t);
    return CompletableFuture.completedFuture(AccountProfile.minimal(customerId));
}
```

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.