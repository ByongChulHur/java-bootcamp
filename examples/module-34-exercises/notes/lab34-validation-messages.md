# Lab 34 — Validation Messages

## Step 1 — Rules
Name required; status required; name min length 2.

## Step 2 — Messages

| Rule | User-facing message |
| --- | --- |
| Name is required | "Please enter a name." |
| Status is required | "Please select a status." |
| Name minimum length 2 | "Name must be at least 2 characters." |

## Step 3 — Timing
Validate on submit: run all checks when the user clicks Save, and also block the submit
if any error exists (not just on blur, since blur-only checks let an unedited invalid
field slip through if the user never focused it).

## Step 4 — Server later
Note: Lab 35 will also show server-side 400 validation errors returned from the API,
in addition to this client-side check.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.