## Reflection Questions

1. Which design decision most affected correctness?
I personally think catch order between BusinessException and Exception is most affect correctness.
If Exception come first, BusinessException also get catch there because it is child class, so 409 conflict become 500 by mistake.

2. What evidence proves the implementation works?
The Main.java print out real JSON for 400, 404, and 409, each one include correlationId lab-request-001.
Also GlobalExceptionHandlerTest pass all four test with no error, so this evidence prove handler mapping is correct.

3. Which failure was hardest to diagnose?
For last, the hardest one was when Failure Experiment 1 make whole program crash instead of showing 500 JSON, 
because service.addCustomer() at top of main() call repository directly and not go through facade, so exception never get caught by any handler.