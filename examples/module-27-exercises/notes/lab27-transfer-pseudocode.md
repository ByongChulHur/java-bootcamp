# Lab 27 — Transfer Pseudocode

## Annotation / method
@Transactional
transfer(fromId, toId, amount, correlationId)

## Force-fail check
if toId == ACC-FORCE-FAIL: throw new IllegalStateException("forced failure")

## Money steps
load Account(fromId) and Account(toId)
from.debit(amount)
to.credit(amount)

## Log step
write TransactionLog(fromId, toId, amount, correlationId) — happens inside the same @Transactional method, after debit/credit, so it commits or rolls back together with the money movement

## Scope
Pre-lab only.