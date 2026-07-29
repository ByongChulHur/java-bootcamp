# Lab 12 starter — timed path (~45 minutes)

**Theme:** Coding standards — smell catalog + refactor messy service

## Copy into your workspace

**Windows (PowerShell)** — from this lab folder:

```powershell
New-Item -ItemType Directory -Force -Path "$env:USERPROFILE\java-bootcamp\examples\lab12-crm" | Out-Null
Copy-Item -Recurse -Force ".\starter\*" "$env:USERPROFILE\java-bootcamp\examples\lab12-crm\"
cd $env:USERPROFILE\java-bootcamp\examples\lab12-crm
```

**macOS / Linux:**

```bash
mkdir -p ~/java-bootcamp/examples/lab12-crm
cp -R starter/. ~/java-bootcamp/examples/lab12-crm/
cd ~/java-bootcamp/examples/lab12-crm
```

Full GUIDE: [`../LAB-12-GUIDE.md`](../LAB-12-GUIDE.md)

## 45-minute checklist

- [ ] Confirm `CustomerService.before.java.txt` frozen
- [ ] Fill `docs/smells.md` (≥8 smells)
- [ ] Refactor to `createCustomer` / `getCustomer` / `updateStatus` (remove `doStuff`)
- [ ] Make `CustomerServiceTest` TODOs green
- [ ] Note before/after in `docs/before-after.md`
- [ ] Run smoke test

## Smoke test

```bash
mvn -B test
```

## Timed-path Pass criteria

| Criterion | Pass / Fail |
| --------- | ----------- |
| No `doStuff` remains; clean API present | Pass / Fail |
| Tests green for CUS-1001 / unknown / duplicate | Pass / Fail |
| smells.md has ≥8 items | Pass / Fail |

Continue remaining GUIDE steps as homework / full path if needed.

# Security and Production Review — Lab 12

# Security and Production Review — Lab 12

1. Which inputs are untrusted?
   All the customer fields coming from whoever calls createCustomer such as customerId,
   fullName, email, phone, and status are treated like they could be bad

2. Where is validation enforced now?
   Validation happens in the validateNotBlank helper inside CustomerService.
   Real authentication and authorization are not there yet, that part is still missing,
   right now it is just basic input checks

3. Which values are sensitive?
   Nothing very sensitive right now, just sample emails like amina dot khan at example
   dot com. Everything is stored in memory only, nothing gets written to disk

4. What can be retried safely?
   getCustomer is safe to retry since it only reads data. createCustomer is not safe
   to retry with the same id since it will throw a duplicate error every time

5. What happens on partial failure?
   The service throws an exception right away. There is no half created customer
   sitting around with missing fields or anything weird like that

6. What would an operator watch later?
   The correlation id such as lab request 001 and how often getCustomer and
   createCustomer throw errors. That tells you if something is actually broken

7. What is not okay for production?
   Storing everything in memory using a Map, since restarting wipes all the data.
   Also there is no real logging framework yet, it still relies on basic messages
   instead of proper structured logs

8. How would contracts get versioned later?
   Lab 13 and beyond will probably use WSDL or OpenAPI for this. Keeping clean
   method names now such as createCustomer, getCustomer, and updateStatus should
   make that easier later