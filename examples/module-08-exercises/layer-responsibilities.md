Step 1 — Assign the tasks
Task	Layer
Accept future create-customer input -> Controller
Reject blank customer name -> Service
Find customer by ID -> Repository
Represent customer ID/name/status -> Entity 
Represent create request fields ->DTO
Define customer-not-found failure -> Exception
Wire application objects later	-> Config

Step 3 — Repair a “god controller”

Bad Flow
Controller validates every business rule
→ edits an in-memory list directly
→ constructs database queries
→ formats errors

Good Flow
Controller maps request
→ Service validates/orchestrates
→ Repository saves/finds
→ Service returns result
→ Controller maps response

Step 4 Explain why boundaries help

isolated testing;
->Splitting and isolates each other means that each layer can tested in isolation

replacing storage without changing controller;
Storage can be swapped by changing only the repository without touching the controller

keeping transport concerns out of business logic;
Keeping transport concerns like HTTP status codes out of the service keeps business logic reuseable outside a web context.

making ownership discoverable.
Clear boundaries also make ownership discoverable, so a new developer can guess that validation lives in service and persistence in repository.