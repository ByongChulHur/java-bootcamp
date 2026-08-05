1. Which design decision most affected correctness?

Letting the filter own MDC clearing was the safest choice, since making every service clean up its own keys would risk someone forgetting one.

2. What proves support can search a request?

CUS-1001 and CUS-1002 logs both carried the same correlation ID with no PII, so one ID is enough to trace the whole request.

3. Which failure was hardest to diagnose?

The MDC leak experiment was hardest, because the thread kept changing so it barely reproduced, showing this kind of bug only hits production sometimes.