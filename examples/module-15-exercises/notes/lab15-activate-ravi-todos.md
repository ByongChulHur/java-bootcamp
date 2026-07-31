# Lab 15 — Fill Activate Ravi Pseudocode TODOs

## Step 1 — Copy pseudocode

customer = repo.findById(CUS-1002)
if customer is null → throw CustomerNotFoundException
if status is not PROSPECT → throw IllegalStatusTransitionException
set status to ACTIVE
repo.save(customer)
log correlation lab-request-001

## Step 2 — Fill blanks

Done on above

## Step 3 — Repo boundary note

Repository saves state; it does not decide PROSPECT→ACTIVE. 

## Step 4 — Self-check

Ravi (CUS-1002) starts PROSPECT and ends ACTIVE in the filled sheet above —
confirmed: findById brings him back as PROSPECT, the status check passes,
and he ends up ACTIVE after repo.save.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.