# Reflection Questions — Lab 12

1. Which design decision most affected correctness?
   I think change List to Map was biggest one. This fix the equals versus double
   equals bug because Map get use equals not object identity.

2. Which smell was hardest to justify removing?
   The status as string was hard because it look like working fine already. But
   change to enum remove typo bugs so I think it was worth it.

3. What evidence proves the refactor preserves intended behavior?
   The three test pass green. Also manual demo show create and get and update
   work for Amina and Ravi. Also failure experiment show duplicate and blank and
   unknown id all fail correct way.

4. What breaks first at ten times method length if smells return?
   I think mixed responsibility smell break first. If method get few more times as an example then
   longer validation and create and update logic all mix together even more, and
   one small change can break three different behavior at same time.

5. Which concern should move to shared infrastructure?
   Correlation id and logging. Right now it just hardcoded string in message, but
   real system should have this come from shared logging system instead.

6. What must change before real customer data is used?
   In memory storage need change to real database. Also need real authentication
   because right now anyone can create or read anything.

7. How does this lab connect to Labs 8 through 11 standards and Lab 13 contracts?
   This lab use naming and structure standard from Lab 8 on messy class. Having
   clean method now should make it easier to use this service for SOAP contract
   in Lab 13 later.

8. What metric or log field or support clue matters most after refactor?
   The correlation id in not found and duplicate error message. If customer have
   problem again support can search this id and see what happened.

9. Which deferred SOLID step comes next and why not today?
   Dependency inversion, like making CustomerRepository interface so storage not
   hardcoded as Map inside service.