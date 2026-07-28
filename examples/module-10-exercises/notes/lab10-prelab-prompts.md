Step 1
"Write a customer class."

Weak prompt is bad because it doesn't specify package, doesn't specify JDK version,
and also it doesn't specify annotations which could lead to incorrect implementations.
Copilot could invent the wrong package, use outdated JDK APIs, or add fake/unnecessary annotations.

Step 2 -Strong prompt

Plain Java 21 record for Northstar CRM customer.
Customer: CUS-1001, Amina Khan, status ACTIVE.
Fields: id, fullName, status only.
No Spring, no JPA.
Correlation note "lab-request-001" — put in a comment only, not a field.

Step 3 Diff the asks
1. JDK version — Java 21
2. Domain fixtures — CUS-1001, Amina Khan, status ACTIVE
3. No-framework boundary — no Spring, no JPA