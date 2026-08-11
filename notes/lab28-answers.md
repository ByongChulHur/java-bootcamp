## Reflection

1. Stateless JWT vs session: choosing STATELESS was the biggest correctness driver — no server-side session state means every request is judged purely on the token's signature and claims, which made the 401/403 boundary predictable to test.

2. Evidence for role separation: the same /api/admin/ping endpoint returned 403 for agent1 and 200 for admin1 using tokens issued from the same login flow — only the role claim differed.

3. Hardest failure to diagnose: 403 was silently turned into 401 because /error wasn't in permitAll — AccessDeniedHandler logged "403" internally but the client saw 401 after Spring forwarded to /error and hit anyRequest().authenticated(). Found via DEBUG security logging.