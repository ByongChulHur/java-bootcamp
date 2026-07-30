# SOAP design notes — Lab 13

## TODO
1. Contract-first vs code-first for partners
I decided to use contract-first. This means that I can prevent partner's integration break if our internal java code changes later and this is mainly because
the contract itself stays stable.

2. Document/literal choice
I used document/literal wrapped style since it produces clean and predictable XML shapes.

3. Correlation placement (`lab-request-001`)
`correlationId` is placed as a body field on each request for simplicity in this contract.

4. Fault shapes: not-found vs validation
`fault-customerNotFound` returns `faultcode=Client` when a requested customerId doesn't exist. `fault-validation` 
also returns `faultcode=Client` but for missing or invalid required input.

5. What Lab 24 will host vs what stays static here 
Since the current lab is only for static contract files such as customer.xsd, CustomerService.wsdl, and sample envelopes and no actual server is running. 
The actual lab which 24 will implement a real Spring-WS @Endpoint against this exact contract.


## Failure Experiments

1. **Broke schemaLocation**
   For this experiment I changed customer.xsd → customerXX.xsd in CustomerService.wsdl. After this change 
   IntelliJ's error/warning count increased. Restored to`customer.xsd` → error count dropped back down.
   Based on this experiment, I learned that the XSD must stay in the same folder as the WSDL with an exact
   filename match, or the schema reference breaks.

2. **Sent empty customerId** in getCustomerRequest
   For this experiment I left customerId empty in getCustomerRequest. The XML still
   passed as well-formed, but it doesn't make sense as a real request. Based on this
   experiment, I learned that "well-formed XML" is not the same as "valid input" —
   the future service (Lab 24) needs to check for this itself.

3. **Compared retry safety between Create and Get**
   For this experiment I thought about what happens if I send CreateCustomer twice
   vs GetCustomer twice. CreateCustomer has no customerId, so sending it twice could
   create a duplicate customer. GetCustomer just reads data, so sending it twice is
   always safe. Based on this experiment, I learned that Create is risky to retry,
   but Get is not.