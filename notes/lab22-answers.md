## Reflection Questions

1. Which design decision most affected correctness?
   Used constructor injection with final fields. locked deps in so they cant change by accident and let me test with new directly no Spring needed.

2. What evidence proves the graph works?
   CustomerServiceTest passed in 0.121s. POST and GET both returned CUS-1001 correctly. logs showed CustomerService ready and notification log firing too.

3. Which failure was hardest to diagnose?
   Hardest part was just getting pom.xml and gitignore to upload right wasted time on that. the actual code errors like missing Service and Repository imports were easy since compiler said cannot find symbol straight up.