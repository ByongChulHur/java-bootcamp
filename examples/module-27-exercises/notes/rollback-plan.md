# Lab 27 — Rollback Evidence Plan

## Before measurement
Record ACC-1001-MAIN's balance before doing anything (this is the "original" number to compare against later).

## Force-fail action
Send a transfer request from ACC-1001-MAIN to ACC-FORCE-FAIL — this destination is designed to throw an exception on purpose.

## After assertions
Check two things: (1) ACC-1001-MAIN's balance is exactly the same as the "before" number — nothing changed. (2) No new "success" row was added to TransactionLog for this attempt.

## Evidence location
Save the before/after balance numbers (or screenshots) in notes/screenshots/lab-27/.

## Scope
Pre-lab only.