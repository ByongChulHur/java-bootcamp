Which design decision most affected correctness of the skeleton?
I personally think constructor injection is most important. Because of this, every class must show what it need through constructor, 
and this make the layer direction easy to check.

Which failure was hardest to diagnose (pathing, packages, POM)?
Pathing was hardest for me. I make folder in wrong place first and it didn't give error, just quietly run wrong. Because of this it take time to notice.

What evidence proves the layered structure is real, not only aspirational?
`mvn clean compile` succeed and Main print correct banner. This prove packages exist and connect right direction, not just look nice on paper.

What breaks first at ten times the team size if packages are messy?
I personally believe merge conflict and unclear ownership break first. Also code review get harder because reviewer cannot assume file only touch one layer.

Which concern should move to shared infrastructure later?
Persistence concern in CustomerRepository should move to shared infrastructure, like connection pooling and transaction, handled by framework instead of by hand.

What must change before real customer data is used?
Repository stub need real logic instead of throwing exception, and dto need validation like email format. Also need encryption rule for PII before real data touch this.

How does this lab connect to Labs 9–12 and later CRM platform pieces?
This lab build the empty room and later lab move furniture in. Since we already build the skeleton of the project, later lab can just add code without restructure.

What metric, log field, query plan, or UI state matters most once APIs exist?
I think the correlation ID like lab-request-001 matter most. Without it logged at each layer, failure become very hard to trace back to specific request.

Why keep DTOs separate from entities for creating Amina Khan (CUS-1001)?
DTO which is also Request/Response shows what API recieve and return. However the entity shows what actually get saved in database.
These two changes for different reasons. For example, if I do not use DTO and just expose entity to the API then every time
database schema changes, the API response could break too.

(Forward look) When Spring Boot arrives, which packages stay stable vs which files change first?
I think the entity and dto will stay the stable because what they represent is domain shape not the framework.
Instead the repository and config will chnage first.


Failure Experiments — Lab 8

Perform each experiment, observe the result, then restore working code.

Experiment 1 — Missing/renamed pom.xml

Action: Renamed pom.xml to fail.xml, then ran mvn clean compile.
Observed: Maven failed immediately because it could not locate a pom.xml in the current directory. Maven requires that exact filename to identify a project — renaming it makes the folder look like it is not a Maven project at all.
Restore: Renamed fail.xml back to pom.xml; mvn clean compile succeeded again afterward.


Experiment 3 — Compile twice in a row
Action: Ran mvn clean compile twice back to back.
Observed: Both runs produced BUILD SUCCESS. clean removes the target/ directory before each build, so the second run starts from the same clean state as the first — output is fully reproducible from source.
Restore: Not needed; both runs are expected to succeed.

Experiment 4 — Wrong-direction import

Action: Added import ...controller.CustomerController; to CustomerRepository.java, ran mvn clean compile.
Result: Compiled fine.
Why it's a problem: Compiler only checks syntax, not layer rules. Repository shouldn't know about Controller (wrong direction), but the compiler can't catch that — needs human code review instead.
Restore: Removed the import, compiled fine again.