# Lab 12 — Smell Bingo

## Step 1 — Smell list

Bingo card: long method, magic strings for ACTIVE/PROSPECT, == on Strings, mixed I/O in domain, unclear names.

## Step 2 — Fixture tie-in

- Long Method: fixing one part (e.g. email logic) risks accidentally breaking the
  status-change logic for Ravi (CUS-1002) since everything is tangled together.
- Magic Strings: a typo like "Active" instead of "ACTIVE" would silently fail the
  status check for Amina (CUS-1001), even though her real status is correct.
- == on Strings: comparing customer.getStatus() == "PROSPECT" may fail for Ravi
  (CUS-1002) if the string from the database isn't the same object as the literal,
  silently preventing his PROSPECT -> ACTIVE transition.
- Mixed I/O: refactoring persistence/logging code risks accidentally touching the
  status-transition rule itself for either customer.
- Unclear names: a method named process() gives no hint it changes customer status,
  risking misuse that corrupts CUS-1001/CUS-1002 data.

## Step 3 — Priority

It  have to be 
1. == on String
2. Magic String for ACTIVE/PROSPECT

Main reason is that these can silently give us wrong result while other smells are for focused on making the code harder to read.
Long Method, Mixed I/O, and Unclear names are real problems too, but they affect maintainability more than correctness.
## Scope
Pre-lab only — do not finish the full graded lab in this exercise.