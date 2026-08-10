# Lab 24 — UsernameToken Plan

## Where credentials live
Inside the SOAP Header, in a wsse:UsernameToken element (Username +
Password), not in the Body.

## Success case
Secured GetCustomer request for CUS-1001, with a valid UsernameToken
(crm-partner / lab24-shared-secret), succeeds and reaches CustomerEndpoint.

## Failure case
Missing or wrong UsernameToken is rejected by the Wss4j interceptor
before the request reaches CustomerEndpoint — client gets a security
fault, not a business fault.

## Out of scope
Full XML signatures, SAML, OAuth IdP integration, and JWT (JWT is for
REST, covered later in Lab 28).

## Scope
Pre-lab only.