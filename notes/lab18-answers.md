## Reflection  Question

1. Which design decision most affected correctness (shared mock repo vs @InjectMocks alone)? 

During this lab, sharing one mock repository between validator and service was the most important decision. If they used different mocks, the validator stubs would never
hit and duplicate check would always pass. This would make the test fail to catch the duplicate email case, which is the main point of this test.

2. What evidence proves the implementation works (captor values, never().save)?
The ArgumentCaptor on addCustomer proved the saved customer really had CUS-1001, Amina name, and ACTIVE status, not just some Customer object. verify never save on the not-found and 
illegal-transition paths was also strong evidence because it proved save never happened, not just that an exception was thrown.


3. Which failure was hardest to diagnose (UnnecessaryStubbing, wrong verify count, …)?
The hardest one was calling changeStatus twice on the same stubbed Ravi. I expected a verify count error but got a different exception instead. 
Because of this reason, the mock kept returning the same object both times and the first call already changed its status before the second call ran.
