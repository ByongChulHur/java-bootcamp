Step 1~2

Which profile is active when you run plain mvn package?

dev

How do you activate prod on the command line?

mvn -Pprod ..

What is the app.env value under dev?

dev

What is the app.env value under prod?

prod


Step 3

Step 3 — Spot the mistakes
Explain why each is dangerous:

1. putting production database passwords inside the dev profile;
If we put DB password inside the dev profile it is a serious security problem. This is because dev is active by default on every engineer's laptop. If real production
Password are placed in dev profile then they are committed into pom.xml and expose their source repository for anyone with repo access to see. 

2. making prod activeByDefault on every engineer laptop;
If a developer runs a plain mvn package expecting the usual dev build, they'd unknowingly build with production settings instead.
This risks accidentally connecting to production systems during local testing.

3. assuming profiles change Java package names (they do not — they change build/config properties);
This is a misunderstanding. Profiles only change build/config properties. They never change the actual Java package structure.

4. documenting secrets in screenshots of profile properties.
   Screenshots are easily shared and can be persist in email threads. Once a screenshot that has a password or API key is shared, it is impossible to fully
revoke the access and it will be accessible to anyone who can see.