# Lab 9 — Maven Build and Dependencies (Northstar CRM)

**Theme:** Maven build lifecycle, dependency scopes, plugins, profiles

## Run

```powershell
mvn -q clean package
java -jar target\customer-service.jar
```

Expected output: `Northstar CRM skeleton — Lab 8` banner with seven packages
and sample customer IDs (CUS-1001, CUS-1002).

## Cleanup

```powershell
mvn clean
```

## CI note (preview — pipelines deepen in later modules)

Preferred verify command on agents:

    mvn -B verify

`-B` is batch mode (non-interactive). Prefer `verify` over `install` on CI
unless your pipeline intentionally publishes to an artifact repository.
Never deploy snapshots from a developer laptop without agreement.

Artifact coordinates: com.northstar:customer-service:0.1.0-SNAPSHOT
Sample customer IDs (docs only): CUS-1001, CUS-1002
Correlation ID (logs later): lab-request-001



## Security and Production Review

1. Which test data is safe to commit, and why (CUS-1001 / CUS-1002)?
Those test datas are safe to commit. The reasons is because those datas are fake fixture IDs which is not real customer data. Amina Khan and Ravi Singh is made up names too
2. and their email also uses example.com domain.

2. Where is human review enforced before AI tests/refactors merge?
Human review happen in copilot notes and ai-test-refactor-notes.md. Every time I use Copilot to make a test or refactor what I have already. I need to check
it myself before accepting.

3. What risk does an always-green trivial test create?
A test like assertNotNull will give use pass no matter what we do, so it gives us false confidence. It makes people think code is being tested and safe, but
it is not checking any of the business logic we have.

4. What is the risk of accepting a refactor without before/after suite runs?
Without running test before and after refactor, we cannot prove behavior stay same. I ran full suite before and after extracting validateCustomerId() 
and adding CustomerNotifier to confirm nothing broke. If I did not do this, a refactor could silently break something and nobody know until it fails.

5. Which values must never appear in tests or mocks?
Real SSNs, real passwords, real customer emails/phone numbers, and any
real production data must not appear. We only use fixture ID such as
CUS-1001/CUS-1002, and lab-request-001 for correlation ID.

6. What would a tech lead audit for meaningful coverage?
   A tech lead would check if assertions actually test real business logic,
   not just checking for null or true. 

7. How does mocking CustomerNotifier reduce coupling vs concrete implementations?
   Because CustomerService depends on the CustomerNotifier interface instead
   of a concrete class like a real email sender, we can test CustomerService's
   behavior without needing a real email server or network connection. 

8. How do you keep an audit trail of AI-suggested vs human-verified test code?
   The copilot-notes/ai-test-refactor-notes.md file itself is the audit
   trail. Every entry (lab11-001 to lab11-004) log what prompt I used, what
   Copilot gave back, whether I accept/reject/fix it, and why. 
