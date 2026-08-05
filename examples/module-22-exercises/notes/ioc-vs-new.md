# Lab 22 — IoC Versus Manual Wiring

| Approach | Who creates collaborators? | Test impact |
| --- | --- | --- |
| Manual `new` | Service constructs InMemoryCustomerRepository | Hard to swap fakes |
| IoC / DI | Spring (or test) supplies collaborators | Constructor takes a fake repo |

## Smell (one sentence)
CustomerService directly instantiates its dependency, making it hard to test with fakes.

## Fix (one sentence)
Inject the CustomerRepository dependency through the constructor, allowing the container or test to provide a fake implementation.
## Scope
Pre-lab only.