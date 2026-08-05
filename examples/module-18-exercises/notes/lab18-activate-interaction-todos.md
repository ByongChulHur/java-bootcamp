# Lab 18 — Fill Activate Interaction Sequence TODOs

1) stub findById(CUS-1002) → ravi PROSPECT
2) call service.activate(…)
3) verify repo.save(customer)
4) verify notifier.notifyActivated(…) // if present
5) assert status ACTIVE
6) ArgumentCaptor status field ACTIVE

## Captor sentence
The ArgumentCaptor proves that the Customer object actually passed to save() carried the ACTIVE status — verify(save) alone only confirms the call happened, not what was inside it.

## Scope
Pre-lab only.