# Lab 24 — SOAP notes

- `@PayloadRoot` must not re-implement lifecycle rules: it only maps XML
  to Java and delegates to CustomerService. Business rules (status
  transitions, validation) live in one place, not duplicated per protocol.
- Fault vs REST ErrorResponse: SOAP returns a structured `<soapenv:Fault>`
  element (faultcode/faultstring); REST returns an HTTP status + JSON body.
  Same underlying exception (IllegalArgumentException), two different
  protocol shapes — never mix the two on the wrong channel.
- Correlation / evidence id: `lab24-001`

## Proof: shared CustomerService (Step 7)

1. Before: REST `GET /api/customers/CUS-1002` → status `PROSPECT`.
2. SOAP: POST `requests/update-customer-status.xml` (secured) →
   `UpdateCustomerStatusRequest` for CUS-1002, status `ACTIVE`.
   Response: `UpdateCustomerStatusResponse` with status `ACTIVE`.
3. After: REST `GET /api/customers/CUS-1002` → status now `ACTIVE`.

This proves REST (`CustomerController`) and SOAP (`CustomerEndpoint`)
both delegate to the same `CustomerService` bean and the same in-memory
store — there is no forked/second repository, so Amina and Ravi's data
never splits between protocols.


Failure Experiment Evidence

Experiment 1: Request while app is stopped

What: Stopped the server (Ctrl+C), then sent get-customer-secured.xml
Observation: curl: (7) Failed to connect to localhost port 8080 — connection refused
Recovery: Restarted with mvn spring-boot:run; requests succeed again

Experiment 3: Request without security header

What: Sent get-customer.xml (no UsernameToken)
Observation: SOAP Fault, faultstring "No WS-Security header found" — rejected before reaching CustomerEndpoint
Recovery: Resent with get-customer-secured.xml (valid UsernameToken); succeeds

Experiment 4: Request for a non-existent customer

What: Sent get-customer-not-found.xml (CUS-9999, secured)
Observation: SOAP Fault, faultcode Client, faultstring "Customer not found" — no stack trace
Recovery: Used a known id (CUS-1001 or CUS-1002) instead