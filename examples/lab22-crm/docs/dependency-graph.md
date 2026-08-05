# Lab 22 — Dependency graph

## Bean edges (fill in)

- `CrmApplication` scans `com.northstar.crm`
- TODO: `CustomerController` → `CustomerService`
- TODO: `CustomerService` → `CustomerRepository` / `InMemoryCustomerRepository`
- TODO: `CustomerService` → `NotificationService`

## Fixtures

- `CUS-1001` Amina Khan ACTIVE
- `CUS-1002` Ravi Singh PROSPECT
- Correlation: `lab-request-001`

## Why constructor injection

Constructor injection makes `CustomerRepository` and `NotificationService` required
at construction time, so `CustomerServiceTest` can call
`new CustomerService(fakeRepo, fakeNotify)` directly in pure Java without starting
Spring. Field `@Autowired` only works when the Spring container performs reflection
to fill the field — without Spring, the field stays null and the class cannot be
tested at all. Running `CustomerServiceTest` confirmed this: it passed in 0.121s
with no Tomcat/Spring context started.

## Failure Experiment Evidence

**Experiment #1 — Removed `@Repository` from InMemoryCustomerRepository**

Observed: Application failed to start with:

Explanation: Removing the stereotype annotation stops Component Scanning from
registering `InMemoryCustomerRepository` as a bean. This is because customerService constructor requires a 
CustomerRepository bean and the customerController constructor requires Service the failure cascades up the whol chain.

Experiment #2:
Sending a create request without name/status succeeded with no error, showing the CRM currently has no input validation.

Experiment #3:
Re-creating CUS-1001 with a different name silently overwrote the original record instead of throwing a duplicate error.