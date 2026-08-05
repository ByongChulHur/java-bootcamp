1. Which design decision most affected correctness (Page Object vs inline locators)?
I personally think using Page Object instead of inline locator was most important decision,
because when locator break, I only need to fix one place instead of many test file.

2. What evidence proves the implementation works?
The evidence is mvn clean verify showing 5/5 test pass (3 API IT and 2 UI IT),
plus screenshot when I broke locator on purpose and suite turn red then green again.

3. Which failure was hardest to diagnose (driver mismatch, wait timeout, API JSON)?
For last, the hardest one to diagnose was wait timeout, because error message just say "timeout waiting for element"
and I need to check html file, controller, and page object one by one to find real problem, which was missing closing bracket in customers.html.