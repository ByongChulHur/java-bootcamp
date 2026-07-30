## Reflection Questions

1. **Which design decision most affected correctness?**
   Making validation run before mapping and saving was the biggest one. When I commented out the validate call to test it, a bad email got saved anyway with no error at all.

2. **What evidence proves the implementation works?**
   All the tests that were checked by CustomerRequestionValidationTest.java tests all pass, and when I ran Main, it created and fetched CUS-1001/CUS-1002 correctly, 
   and showed the correlation ID in the error message for the bad email which suppose to show error message.

3. **Which failure was hardest to diagnose?**
   Not sure since I did not have any failure while I was working through this lab 14. Everything worked as expected once the code was written correctly.