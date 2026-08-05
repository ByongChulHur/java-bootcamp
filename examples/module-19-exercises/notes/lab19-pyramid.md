# Lab 19 — Test Pyramid for CRM

## Base (unit)
Many fast JUnit/Mockito tests from Labs 17–18, covering CustomerService
validation rules in isolation with mocked repositories.

## Middle (API IT)
CustomerApiIT. A small number of Spring HTTP integration tests
that start a real server with TestRestTemplate and verify the
customer create and get endpoints.

## Top (UI)
A small number of Selenium journeys. Confirm Amina shows up
correctly on the customer status page. Confirm Ravi can be
activated through the UI button flow.

## Scope
Pre-lab only.