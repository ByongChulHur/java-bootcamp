# Lab 27 — Transaction Boundary Placement

## Place annotation on
TransferService.transfer(fromId, toId, amount)

## Avoid
TransferController (HTTP layer)

## Why (one sentence)
@Transactional needs Spring's proxy, which only works on service beans; controllers should stay thin.

## Self-invocation risk
Calling this.transfer() from inside the same class skips the proxy — @Transactional silently ignored.

## Scope
Pre-lab only.****