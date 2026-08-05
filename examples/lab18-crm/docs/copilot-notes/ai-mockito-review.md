# AI Mockito Review

## Entry ID
lab18-001

## Prompt
"Generate a Mockito test that mocks CustomerRepository for DefaultCustomerService
duplicate-email path. Verify existsByEmail and that save is never called."

## Result
Copilot generated `addCustomerDuplicateEmailNeverSaves()`. In the ai generated code, it had stubs existsById(false)
and existsByEmail(true), asserts BusinessException, verifies existsByEmail was called and save was never called.

## Check
1. Mocked the class under test? No, only CustomerRepository is @Mock.
2. Stubs minimal (no unused when)? Yes, both existsById and existsByEmail are used.
3. Verification matches real validator call order? Yes, existsById checked first which it was
   stubbed false, then existsByEmail checked second which was stubbed true, where it fails.
4. Thread.sleep or real DB? None.
5. Ran mvn -q test after accepting? Yes as a result it showed, 4 tests, 0 failures.

## Decision
Accepted. mvn test passed (4 tests, 0 failures).