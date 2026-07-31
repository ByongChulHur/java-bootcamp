
Reflection Questions
1. Which design decision most affected correctness?

I personally think putting ALLOWED transition map inside CustomerValidator instead of repository is decision that affected correctness most.
Because of this Repository stay dumb and only save/find, and every illegal transition get catch in one place before it touch the Map.

2. What evidence proves the implementation works?

Main output prove it: CUS-1002 activate PROSPECT to ACTIVE, and illegal ACTIVE→PROSPECT on CUS-1001 throw exception with lab-request-001 while status stay ACTIVE. 
Also CustomerValidatorTest tests all pass and grep show no HashMap in service package.

3. Which failure was hardest to diagnose?

For last, split-repo-instance failure was hardest one. Because code compile fine and no exception throw, duplicate email just pass through silently. 
I only find this bug after I actually run and test it myself.