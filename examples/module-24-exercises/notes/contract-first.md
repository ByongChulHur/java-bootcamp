# Lab 24 — Contract-First Recall

## Order (fill)
1. Author customer.xsd by hand
2. Generate JAXB Java classes from the XSD (mvn generate-sources)
3. Implement @Endpoint using the generated JAXB types
4. Serve WSDL dynamically at runtime from the same XSD

## Source of truth
customer.xsd is the single source of truth — not hand-written Java DTO fields.

## Why partners care
Partner billing tools bind to fixed XML element names (e.g. GetCustomerRequest).
If we change only the Java code without updating the XSD, the contract drifts
and partner requests fail silently, with no clear error.

## Scope
Pre-lab only.