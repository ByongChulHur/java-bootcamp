# Lab 18 — isolation policy

## What we mock

- `CustomerRepository` (I/O boundary)

## What we keep real

- `CustomerValidator` (domain rules)
- `DefaultCustomerService` (class under test — never mock it)

## TODO

Add one paragraph: when you prefer Lab 17 real-repo tests vs Lab 18 mocks.
## When to use which

LAb 17 which is real repo test is good when you want to confirm whole flow works end with real in memory data store which is good for happy path confidence.
Lab 18 which is Mockito tests is good when I need to isolate one specific branch of logic. For example the not found path, and the duplicate email path.
In these example cases which repository calls did or did not happen. Because of this reason, mocks make edge cases and negative paths fast and easy to
control, and a real repository can't do this as precisely.

## Stub vs verify

Use `when(...).thenReturn(...)` to set up what a mock hands back. Use
`verify(...)` when the interaction itself is the thing being tested.

## Correlation ID on exception paths

Every `BusinessException` carries the `correlationId` passed into the
call, so a failed request can be traced in logs.