## lab11-001

**Prompt used:** "Add one more test to CustomerServiceTest."

Based on the given prompted, Copilot generated the following test method:

`listAllReturnsUnmodifiableList()`. The following method was verified 'listAll()' returns an unmodifiable list which I think
it was a test I could actually fail. So I ended up keeping this test.

Give example of False test case:
@Test
void serviceIsNotNull() {
assertNotNull(service);
}

Why is the following test is false confidence?
The given example test case is false confidence because it does not really check any business behavior. So it cannot fail regardless of errors in "CustomerService".

Action Taken : Deleted the false confidence test case and kept the valid one.


## lab11-002
Smell name: The blank customer ID check was written directly inside addCustomer,
and the same logic was needed again in updateStatus which is duplicated validation logic.

Refactor: Extracted that check into a single validateCustomerId() method, and had both addCustomer and updateStatus call it instead of
writing the same code twice.

Proof: I ran both CustomerServiceTest and CustomerServiceIntegrationTest to ensure all tests still pass after the refactor. I think this shows that the code
structure got improved but the actual behavior is the same.

## lab11-003

**Asked Copilot:** "What CustomerService behavior is not covered by
CustomerServiceTest and CustomerNotifierMockTest?"

**Copilot's answer:**
- addCustomer rejects null/blank customerId (validateCustomerId -> IllegalArgumentException) — not tested
- updateStatus rejects null/blank customerId (validateCustomerId -> IllegalArgumentException) — not tested
- findByStatus returns only customers with the requested CustomerStatus — not directly tested
- findByCustomerId returns an empty Optional for unknown IDs — not directly tested
- addCustomer's return value (it returns the Customer) is not asserted
- create(...) and getById(...) stubs are not tested
- Default-constructor notifier's no-op behavior is not explicitly asserted

### Customer.java
| getters/setters (customerId, fullName, email, phone, status, createdAt) | No, I did not test these directly |
| equals() | Yes, I tested same customerId means equal even other fields different |
| hashCode() | No, not tested |
| toString() | Yes, tested it contains customerId |

### CustomerService.java

| addCustomer() | Partial — I tested normal add and duplicate ID case, but blank-ID case and return value not tested |
| findByCustomerId() | Partial — used indirectly in other tests, but empty-Optional case for unknown ID not tested directly |
| findByStatus() | No, I don't have dedicated test for this yet |
| updateStatus() | Partial — tested success, unknown-ID error, and notifier call, but blank-ID case not tested |
| listAll() | Yes, tested it return unmodifiable list |
| create() / getById() | Not applicable, these are Lab 8/9 stubs and out of scope for this lab |

**My decision about these gaps:** 
I decide to accept these gaps for now. The main reason for this decision is that we know Lab 11 is only exploratory preview, 
not full coverage testing. Because of this reason, I think testing the most important business rule is enough for now such as duplicate rejection, status update, and notifier interaction, 
and these are already tested. 

## lab11-004 — Acceptance guidelines for AI-generated tests and refactors

1. Every assertion must be able to fail — if I can't describe an input that
   breaks it, it isn't a real test.
2. Every refactor must be backed by a passing test suite run before and after.
3. No accepted suggestion may introduce a dependency not already in pom.xml.
4. I can explain, without re-reading Copilot's explanation, why the code
   is correct.
5. Coverage gaps are documented, not silently ignored.


## Failure experiment

I removed the notifier.notifyStatusChange(...) call from updateStatus to
see what happens. CustomerNotifierMockTest failed as expected, with
Mockito saying "Wanted but not invoked... Actually, there were zero
interactions with this mock." This proves the mock test really checks
the notifier interaction, not just a false confidence test. Then I put
the line back, and ran mvn clean test again. For result, I got all 8 tests passed.