## Reflection

1. **Which design decision most affected correctness?**
   CustomerService.create() has no duplicate ID check, so it just overwrites. Confirmed in Experiment 2. Would be a real bug in production.

2. **What evidence proves the implementation works?**
   mvn test passed twice with Tests run: 2. Confirmed CUS-1001/CUS-1002 create+get with Invoke-RestMethod, and /actuator/health returned UP.

3. **Which failure was hardest to diagnose?**
   PowerShell curl.exe JSON escaping issue. POST kept returning 400, but a GET right after showed seed data, which confused me for a while before I found the real problem.