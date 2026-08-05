# Lab 18 — Mockito Anti-Patterns

## Anti-pattern table
| Mistake | Better approach |
| --- | --- |
| Mock the SUT | Mock collaborators only, keep the class under test real |
| Unnecessary stubbing | Stub only what this test actually uses |
| Verifying too much | Verify only interactions that matter to the behavior |
| Mocking a String or enum status | Silly — just use the real value directly |
| Overusing verifyNoMoreInteractions | Use only when interaction completeness is truly part of the contract |

## AI reject rule
Reject any Copilot suggestion that mocks CustomerService while the test is testing CustomerService such as mocking the SUT means no real logic ever runs.

## Scope
Pre-lab only.