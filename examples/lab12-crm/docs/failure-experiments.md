## Experiment 4 — lookup with new String("CUS-1001")

Made a fresh String object with the same value as Amina's id, instead of using
the literal, to see if the old == bug could still happen.

Result:
PS C:\Users\andyh\java-bootcamp\examples\lab12-crm> java -cp target\classes com.northstar.crm.Main
Created: Customer{customerId='CUS-1001', fullName='Amina Khan', status=ACTIVE}
Created: Customer{customerId='CUS-1002', fullName='Ravi Singh', status=PROSPECT}
Fetched CUS-1001: Customer{customerId='CUS-1001', fullName='Amina Khan', status=ACTIVE}
Updated CUS-1002 status: Customer{customerId='CUS-1002', fullName='Ravi Singh', status=ACTIVE}
Expected duplicate failure: Customer already exists: CUS-1001
Expected not-found failure: Customer not found: CUS-9999 correlationId=lab-request-001
Experiment 4 - lookup with new String(): Customer{customerId='CUS-1001', fullName='Amina Khan', status=ACTIVE}

Still found her correctly. If it was an old code based which is == so this would've returned null even
though she exists. Now it uses Map.get() which checks equals(), not ==, so this
bug is gone.

No restore needed, this just confirms the fix works.

## Experiment 3 — create CUS-1001 twice

tried making customer with id that already exist (CUS-1001 again)

result:
PS C:\Users\andyh\java-bootcamp\examples\lab12-crm> java -cp target\classes com.northstar.crm.Main
Created: Customer{customerId='CUS-1001', fullName='Amina Khan', status=ACTIVE}
Created: Customer{customerId='CUS-1002', fullName='Ravi Singh', status=PROSPECT}
Fetched CUS-1001: Customer{customerId='CUS-1001', fullName='Amina Khan', status=ACTIVE}
Updated CUS-1002 status: Customer{customerId='CUS-1002', fullName='Ravi Singh', status=ACTIVE}
Expected duplicate failure: Customer already exists: CUS-1001
Expected not-found failure: Customer not found: CUS-9999 correlationId=lab-request-001
Experiment 3 - duplicate create failed as expected: Customer already exists: CUS-1001

it throw IllegalStateException like it should, not silently do nothing or overwrite.
old code just print "dup" and return null, this way better and clearer now

no need restore, duplicate detection working fine

## Experiment 2 — blank customerId

tried creating customer with empty string as customerId

result:
Experiment 2 - blank customerId rejected as expected: customerId must not be blank

throws IllegalArgumentException like expected, old code just print "bad" and
return null, no explanation why it failed

no restore needed, validation helper working fine