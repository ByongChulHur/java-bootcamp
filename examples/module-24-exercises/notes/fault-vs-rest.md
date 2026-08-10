# Lab 24 — SOAP Fault Versus REST Error

| Case | SOAP | REST |
| --- | --- | --- |
| Missing customer | SOAP Fault (Client, "Customer not found") | 404 JSON problem details |
| Validation fail | SOAP Fault (Client) | 400 JSON |
| Missing UsernameToken | WS-Security fault | 401/403 (later Lab 28) |

## One rule
Same CustomerService exception drives both mappings. Only the protocol
adapter differs — SOAP always returns a SOAP Fault, never a REST-style
JSON body, and vice versa.

## Scope
Pre-lab only.