# Lab 19 — Page Object Sketch

## Class name
CustomerFormPage, CustomerStatusPage

## Actions
CustomerFormPage: open(), fillName(name), fillEmail(email), submit()
CustomerStatusPage: open(customerId), clickActivate()

## Queries
CustomerFormPage: readErrorMessage()
CustomerStatusPage: readStatus()

## Asserts live in
Test methods. Both page objects only return state or perform actions;
deciding whether a result is correct belongs in the test.

## Scope
Pre-lab only.