# Lab 22 — Constructor Injection Preference

## Preferred pattern
Constructor injection is preferred over field injection for dependencies.
CustomerRepository and NotificationService are delcared as final fields and will be provided through the constructor. This makes the dependencies explicit and allows for easier testing.

## Why (testability)
Constructor injection ensures that all dependencies are available when an object of the class is created, making it easier to test with mock objects.

## Avoid
Field injection should be avoided as it can lead to hidden dependencies and make testing more difficult.

## Setter role (one line)
Setter injection is reserved for optional or reconfigurable
dependencies

## Scope
Pre-lab only.