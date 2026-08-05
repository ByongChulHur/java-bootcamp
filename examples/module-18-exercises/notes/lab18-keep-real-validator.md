# Lab 18 — When to Keep Real Validator

## Mock repo?
CustomerRepository is an I/O boundary which in this case, it talks to a database and this is slow and non-deterministic in tests. Mock it.

## Real validator?
CustomerValidator is pure, deterministic domain logic with no external dependencies. Keep it real so the actual business rules are exercised in the test.

## Mock notifier?
CustomerNotifier sends emails/notifications, which is I/O we don't want running during unit tests. Mock it.

## Rule
Mock I/O and unstable dependencies such as database, email, external APIs. This will keep pure domain helpers real when they are cheap, fast, and deterministic.

## Scope
Pre-lab only.