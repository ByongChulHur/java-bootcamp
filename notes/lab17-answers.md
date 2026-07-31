
Reflection Questions
Write 1–3 sentence answers (not essays):

## Which design decision most affected correctness?
    For the following questions, Wiring a new repository in @BeforeEach for every test is the most important that affected correctness. Sharing one repository can make the results depends on test order.

## What evidence proves the implementation works?
    All the mvn clean verify test I tried got resulting 20 tests with BUILD SUCCESS, and JaCoCo reported 97% line coverage.
    Also for the another prove that I can show is that when I changed the minimum coverage rate to 0.99, 
    it caused real build failure which clearly proves the gate actually works and check coverage rather than always passing

## Which failure was hardest to diagnose?
    One thing that was hardest to diagnose was that I forget to add Amina before testing the illegal transition. Since changeStatus calls findById first, 
    the test would have failed by showing not-found error instead of the illegal transition error which meant to be checked.