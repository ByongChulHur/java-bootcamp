# Lab 25 — Service Layer Skeleton

## Constructor deps
CustomerService's constructor takes a CustomerRepository parameter and stores it via
constructor injection. It depends on the interface (CustomerRepository), not a concrete
class (InMemoryCustomerRepository), and never creates the repository itself with `new`
inside the service.

## create TODO
The create(customer) method first calls repository.existsById(customer.getId()) to check
for a duplicate. If it already exists, it throws IllegalStateException("duplicate") and
rejects the request. If it does not exist, it calls repository.save(customer) to delegate
the save. This order (check first, then save) must always be followed, and this judgment
(a business rule) belongs to the Service, not the Repository.

## get TODO
The get(id) method calls repository.findById(id). If no result is found (e.g. a
non-existent id like CUS-9999), it throws a not-found exception (e.g.
ResourceNotFoundException). If a result is found, it returns the retrieved Customer.
It uses Optional instead of returning null, and handles the not-found case explicitly
with .orElseThrow(...).

## Forbidden in this class
CustomerService must never contain HTTP-related types (ResponseEntity, @RequestBody,
@RestController, etc.). Service deals only with pure business logic — deciding HTTP
status codes or response formatting is the Controller's responsibility. If Service started
returning ResponseEntity, it could no longer be reused by non-HTTP callers such as a SOAP
endpoint or a batch job.

## Scope
Pre-lab only.