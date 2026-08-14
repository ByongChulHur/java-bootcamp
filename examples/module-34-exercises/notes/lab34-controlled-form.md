# Lab 34 — Controlled Form Sketch

## Reference

| UI piece | State field |
| --- | --- |
| Name input | name |
| Status select | status |
| Error text | error |
| Submit disabled | isValid derived |

## Step 2 — Flow

1. Render — form renders with current draft values
2. onChange Updates State — typing/selecting updates draft.name or draft.status
3. Validate — check name is non-empty, status is valid
4. onSubmit — preventDefault, run validate, if valid save to customers

## Step 3 — Fixture

Example draft: name `Ravi Singh`, status `ACTIVE` before submit assigns `CUS-1002` (server later).

```jsx
const draft = { name: "Ravi Singh", status: "ACTIVE" };
// render -> onChange updates state -> validate -> onSubmit
// submit assigns CUS-1002 (server will assign real id in Lab 35)
```

## Step 4 — Uncontrolled note

Uncontrolled refs (defaultValue + useRef) are out of scope for this lab path — this lab only uses controlled inputs (value + onChange).

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.