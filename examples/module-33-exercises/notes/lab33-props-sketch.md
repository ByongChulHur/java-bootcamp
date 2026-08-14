# Lab 33 — Props Sketch

## Reference
| Prop | Amina Example | Ravi Example |
| --- | --- | --- |
| customerId | CUS-1001 | CUS-1002 |
| name | Amina Khan | Ravi Singh |
| status | ACTIVE | PROSPECT |
| onSelect | () => void | () => void |

## Step 2 — Types
status: 'ACTIVE' | 'SUSPENDED' | 'PROSPECT';

## Step 3 — Children?
CustomerCard takes props only, not children — all its content
(name, status, action button) comes from typed props, not from
nested JSX content, so its shape stays predictable and easy to test.

## Step 4 — Anti-pattern
Never pass the entire global store as one mega-prop (e.g.
<CustomerCard store={entireAppState} />). Each component should
receive only the specific fields it actually needs.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.