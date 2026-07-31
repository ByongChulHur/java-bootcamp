# Lab 16 — Catch Order

## Step 1 — List types

NotFoundException, ConflictException, ValidationException, Exception.

## Step 2 — Order

1. NotFoundException
2. ConflictException
3. ValidationException
4. Exception (fallback, must be last)

## Step 3 — Why

If the broad catch such as Exception gets placed first, it would shadow the more specific domain exceptions that coming afterward. This will cause all errors
to get hidden by generic 500 response.

## Step 4 — Prep only

*Do not complete full Lab 16 advice wiring in pre-lab.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.