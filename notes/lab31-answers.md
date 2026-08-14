1. Which design decision most affected correctness?
   → Publishing only after the DB save succeeds. Not fully atomic (no outbox pattern), but it prevents false events from going out.

2. What proves once-only processing?
   → The duplicateEventIsIgnored test. Sending the same event twice only triggers real processing once; the second time just logs duplicate_event_ignored.

3. What was hardest?
   → A test bug where markIfNew() was used for verification, causing the test to pass for the wrong reason. Fixed by switching to contains().