Step 1 
1. Client sends a CustomerRequest to CustomerController
2. CustomerController calls CustomerService.createCustomer(request)
3. CustomerService validates the request and assigns a new ID and status
4. CustomerService calls CustomerRepository.save(Customer)
5. CustomerRepository returns the saved Customer back to CustomerService
6. CustomerService builds a CustomerResponse and returns it to CustomerController
7. CustomerController returns that response to the Client

Step 2
Boundary -> Input -> Output
Client → controller	-> Future transport payload	-> CustomerRequest
Service validation	-> Request DTO -> valid domain values
Service → repository -> Customer entity	-> saved entity
Service → controller -> entity/result -> CustomerResponse

Step 3
1. Client sends a request with a blank name to Controller
2. Controller calls Service.createCustomer(request)
3. Service fails validation and returns a "validation failure" to Controller
4. Controller returns a safe error response to the Client (to be defined later)

Step 4

## Now
- Package names and stub responsibilities
- Plain Java types that compile
- Documented flow

## Later
- Spring controller annotations
- Validation annotations
- Repository implementation/JPA
- HTTP response mapping
- Correlation-ID logging