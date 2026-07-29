## Reflection Questions

1. A meaningful test can actually fail if code has bug, like
   addCustomerRejectsDuplicateId will fail if I remove the duplicate check.
   False confidence test like assertNotNull(service) can never fail because
   service is always fresh from @BeforeEach.

2. Before extracting CustomerNotifier, there was no easy way to test what
   happen when status change without using real email system. After
   extracting it, I could use Mockito to make fake notifier and verify
   notifyStatusChange was called with correct arguments.

3. I would tell them Copilot can make tests that look correct but check
   nothing meaningful, or even use methods that don't exist — like when it
   gave me CustomerStatus.INACTIVE which is not real. If they don't check
   every test, they end up with tests that pass but don't catch real bugs.

4. I rejected Copilot's test that used CustomerStatus.INACTIVE, because
   this value doesn't exist in real enum — real values is PROSPECT, ACTIVE,
   SUSPENDED, CLOSED. If I accept without checking, code would not even
   compile.

5. This lab only touch surface of testing — basic JUnit 5, one Mockito
   mock, simple refactor. Labs 17-18 will go deeper like parameterized tests
   and ArgumentCaptor, which I already noted as gap in lab11-003.

6. Not having direct test for blank-ID rejection is acceptable now,
   because important rules like duplicate rejection and status update
   already tested. This will change in Lab 17 when I learn parameterized
   testing for many edge cases at once.

7. This lab build safety net of tests around CustomerService before
   later labs add DTOs and APIs. CustomerNotifier pattern also prepare for
   future labs where real notification like email might get added.

8. If I skip test before/after refactor, I cannot prove nothing broke.
   In shared codebase, other people rely on existing behavior, so silent
   bug could affect whole team and cost more to fix later in production.

9. Even after Spring arrives, depending on interface (CustomerNotifier)
   instead of concrete class still valuable, because Spring's dependency
   injection work the same way. The Mockito pattern I used today will still
   work to test logic without real external service.