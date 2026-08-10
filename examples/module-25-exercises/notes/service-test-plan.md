# Lab 25 — Service Test Plan

| Case | Setup | Expect |
| --- | --- | --- |
| get CUS-1001 | seeded in-memory repo with CUS-1001=Amina Khan/ACTIVE | returns Amina Khan with status ACTIVE |
| duplicate create | repo already contains CUS-1001, attempt create with same id | throws IllegalStateException("duplicate") |
| get CUS-9999 | repo has no entry for CUS-9999 | throws ResourceNotFoundException (not-found) |
| create new | fresh repo (or repo without the new id), create CUS-1003 | customer is saved, retrievable afterward |

## Spring Boot required for unit test?
We dont need it. This is Because CustomerService is a constructor-injected class that only depends on CustomerRepository. 
We can create a fake or in-memory implementation of CustomerRepository and test CustomerService in pure Java without starting Spring Boot, MockMvc, or Tomcat. 
This is the advantage of layered design with constructor injection and interface dependency.

## Scope
Pre-lab only.