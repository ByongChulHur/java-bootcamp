# Lab 34 — Fill useState TODOs

## Step 1 — Paste
Create `notes/lab34-todos.md`:

```tsx
const [name, setName] = useState("");
const [status, setStatus] = useState<'ACTIVE' | 'SUSPENDED'>("ACTIVE");
const [error, setError] = useState<string | null>(null);

function onSubmit(e: FormEvent) {
  e.preventDefault();
  if (!name.trim()) { setError("Name is required"); return; }
  // TODO Lab 35: POST to API
  console.log({ name, status, correlation: "lab-request-001" });
}

<input value={name} onChange={(e) => setName(e.target.value)} />
```

## Step 2 — Fill
Suggested: `""`, `'ACTIVE' | 'SUSPENDED'`, `"ACTIVE"`, `"Name is required"`, `setName`.

## Step 3 — Amina seed
Optional alternate: initialize name to `"Amina Khan"` for an edit form TODO comment.

```tsx
const [name, setName] = useState("Amina Khan");
// TODO edit-form: replace with initialCustomer.name when in edit mode
```

## Step 4 — Lift state note
Add TODO: selectedCustomerId lives in parent list container.

```tsx
// TODO: selectedCustomerId lives in the parent list container, not this form.
```

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.      