# AI Review Notes — Lab 12

## lab12-001

**Prompt used:** "Extract this validation logic into a private helper method"
and specified the area I want to extract which applied to the customerId/fullName blank checks in `createCustomer`

**Suggestion summary:** Copilot extracted the repeated null/blank check into a
private `validateNotBlank(String fieldName, String value)` helper method, and
replaced both inline checks in `createCustomer` with calls to it.

**Verdict:** Accept.

**Risk caught:** Not that I can find. Copilot did not add any Spring code and that the
exception type or behavior did not change.