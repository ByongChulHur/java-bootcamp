# Lab 24 — SOAP Operation Map

| SOAP operation | CustomerService method |
| --- | --- |
| GetCustomer | getById |
| CreateCustomer | create |
| UpdateCustomer | update |
| DeleteCustomer | delete |

## Shared service?
Yes. SOAP (CustomerEndpoint) and REST (CustomerController) both call the
same CustomerService bean. This keeps one shared data store, so a change
made through REST is immediately visible through SOAP, and vice versa.
Using two separate repositories would cause split-brain data for
Amina (CUS-1001) and Ravi (CUS-1002).

## Scope
Pre-lab only.