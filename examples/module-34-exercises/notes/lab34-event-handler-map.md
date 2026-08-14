# Lab 34 — Event Handler Map

## Step 1 — Table
Columns: Event, Handler, State updated.

| Event | Handler | State updated |
| --- | --- | --- |
| Name input onChange | handleNameChange | draft.name |
| Status select onChange | handleStatusChange | draft.status |
| Form onSubmit | handleSubmit | validate() runs, then customers (create or update) |
| Customer row onClick (select Amina, CUS-1001) | handleSelect(id) | mode → { kind: 'edit', id: 'CUS-1001' } |
| Cancel button onClick | handleCancel | draft resets to empty/initial, mode → { kind: 'closed' }, errors cleared |

## Step 2 — Rows
Included: name onChange, status onChange, form onSubmit, row onClick → select Amina (CUS-1001, ACTIVE).
Also added a Cancel row since create/edit modes must stay mutually exclusive.

## Step 3 — Derived
`isValid` is NOT stored in its own useState. It is derived during render from `draft` and `errors`
(e.g. `const isValid = draft.name.trim().length > 0 && !errors.name && !errors.status;`).
Storing it separately would risk it going out of sync with draft/errors.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.