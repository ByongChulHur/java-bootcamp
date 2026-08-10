## Reflection Questions

1. Which design decision most affected correctness (where rules live)?
   Putting duplicate/not-found checks in CustomerService, not the controller, mattered most.
   If controller had that logic, SOAP couldn't reuse it later.

2. What evidence proves layering works?
   CustomerServiceTest passed (2 tests, 0 failures) with no Tomcat, no Spring context.
   Controller also has zero repository imports.

3. Which failure was hardest to diagnose?
   Duplicate and not-found both return 500, so they look the same over HTTP.
   Only the exception type (IllegalStateException vs IllegalArgumentException) tells them apart.