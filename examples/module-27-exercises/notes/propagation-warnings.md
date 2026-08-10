# Lab 27 — Propagation Warnings

| Pattern | Risk |
| --- | --- |
| REQUIRES_NEW on log | Log commits independently even if the money transfer (debit/credit) rolls back — creates a "success" log entry with no actual money moved |
| Self-invocation | Calling this.transfer() from inside the same class skips the AOP proxy — @Transactional is silently ignored |
| Swallow exception | catch(Exception e) that only logs and returns normally means Spring never sees the exception — it commits the partial change instead of rolling back |
| TX on controller | Puts the transaction boundary on the HTTP layer instead of the service — couples persistence to the web layer and breaks reuse |

## Lab default
REQUIRED (Spring's default) on TransferService.transfer is enough — no custom propagation needed for this lab.

## Scope
Pre-lab only.