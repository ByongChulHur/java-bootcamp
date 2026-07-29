# Before / after — Lab 12

## Before
- API: `doStuff` / `get`
- Failures return null; `==` on IDs

**What it did wrong:**
- Bad input just printed "bad" and returned `null`
- Duplicate ID just printed "dup" and returned `null`
- `get()` compared ID strings with `==`, which could fail even for a real customer
- No real logging, just `System.out.println`


## After
- API: `createCustomer` / `getCustomer` / `updateStatus`
- Exceptions for unknown/duplicate
- Typed `Map<String, Customer>`; no `doStuff`

**What it improved and does now:**
- Now it throws IllegalArugmentException with a clear message if the input is bad
- Now it throws IllegalStateException with a clear message if there are duplicate ID
- getCustomer() looks up by key in a map which means that it can avoid == bug
- Updating status has its own clear method called updateStatus


| Problem before                      | How it was fixed |
|-------------------------------------| ----------------- |
| Poor naming (`doStuff`, `data`)     | Renamed to `createCustomer` / `getCustomer` / `updateStatus` |
| URaw types `List`                   | Changed to `Map<String, Customer>` |
| Long method/ mixed responsibilities | Split into 3 clear methods + 1 helper |
| Stringly-typed status               | Changed to `CustomerStatus` enum |
| Incorrect equality (`==`)           | Fixed by using `Map` lookup instead |
| Null as control flow                | Now throws a clear exception instead |
| Side-effect logging                 | Removed; errors carry a correlation ID instead |
| Magic `"UPDATE"` behavior           | Removed; replaced with `updateStatus()` |

## Methods — before vs after

| Before | After |
| ------ | ----- |
| `doStuff(a,b,c,d,e)` | `createCustomer(customerId, fullName, email, phone, status)` |
| `get(id)` | `getCustomer(customerId)` |
| *(didn't exist)* | `updateStatus(customerId, newStatus)` |
| *(didn't exist)* | `validateNotBlank(fieldName, value)` |

## Manual Demo Transcript
```
PS C:\Users\andyh\java-bootcamp\examples\lab12-crm> java -cp target\classes com.northstar.crm.Main
Created: Customer{customerId='CUS-1001', fullName='Amina Khan', status=ACTIVE}
Created: Customer{customerId='CUS-1002', fullName='Ravi Singh', status=PROSPECT}
Fetched CUS-1001: Customer{customerId='CUS-1001', fullName='Amina Khan', status=ACTIVE}
Updated CUS-1002 status: Customer{customerId='CUS-1002', fullName='Ravi Singh', status=ACTIVE}
Expected duplicate failure: Customer already exists: CUS-1001
Expected not-found failure: Customer not found: CUS-9999 correlationId=lab-request-001
```

## Test Output (Running mvn -B test)

PS C:\Users\andyh\java-bootcamp\examples\lab12-crm> mvn -B test
[INFO] Scanning for projects...
[INFO]
[INFO] -------------------< com.northstar:customer-service >-------------------
[INFO] Building Northstar Customer Service 0.1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ customer-service ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ customer-service ---
[INFO] skip non existing resourceDirectory C:\Users\andyh\java-bootcamp\examples\lab12-crm\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ customer-service ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- surefire:3.5.2:test (default-test) @ customer-service ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.northstar.crm.service.CustomerServiceTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.067 s -- in com.northstar.crm.service.CustomerServiceTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.881 s
[INFO] Finished at: 2026-07-29T16:36:54-04:00
[INFO] ------------------------------------------------------------------------


## Test-first status (Step 3)
CustomerServiceTest was written against the target API (createCustomer/getCustomer)
before CustomerService was refactored. At this point the test file does not compile
because CustomerService still only has doStuff/get. This is expected — refactor
comes next in Step 4-5.