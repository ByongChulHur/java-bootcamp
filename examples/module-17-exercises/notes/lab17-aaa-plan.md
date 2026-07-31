# Lab 17 — AAA Service Tests Plan

## Step 1 — Happy path

AAA for activate Ravi PROSPECT → ACTIVE.

First for Arrange, I create a fake repository and the put Rabi into it which is id = CUS-1002 with Status = "PROSPECT"

Second for ACT. I call service,activateCustomer("CUS-1002")

Last step is Assert. In this steps it means what do I expect to be true after the action. So the returned customer's status should be ACTIVE and
The id is still CUS-1002.

## Step 2 — Not found

AAA for CUS-9999 throws not found.

In Arrange steps, Create repository that has Amina with id CUS-1001 and Ravi with id CUS-1002 only so we can make sure CUS-9999 is missing
In Act steps, I call service.activateCustomer("CUS-9999")
For last Assert, throws not-found exception with message "Customer not found"

## Step 3 — Illegal

AAA for illegal transition on Amina ACTIVE.
In Arrange steps, create repository has Amina (CUS-1001, status=ACTIVE already)
In Act steps, it call service.activateCustomer("CUS-1001")
For last Assert steps which is throw a illegal-transition exception, Amina's status stays ACTIVE

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.