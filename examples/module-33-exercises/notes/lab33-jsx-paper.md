# Lab 33 — JSX on Paper

## Step 1 — Tree
<CustomerList>
  <CustomerCard key="CUS-1001">
    <h3>Amina Khan</h3>
    <StatusBadge status="ACTIVE" />
  </CustomerCard>
  <CustomerCard key="CUS-1002">
    <h3>Ravi Singh</h3>
    <StatusBadge status="PROSPECT" />
  </CustomerCard>
</CustomerList>

## Step 2 — Keys
key={customerId} should be "CUS-1001", not the array index, because
customerId is stable and unique to each customer. If the list is
reordered, filtered, or an item is added/removed, an index-based key
would shift and point to the wrong item, causing React to mismatch
state or re-render the wrong card. customerId never changes for a
given customer, so it's a safe, stable identity for React to track.

## Step 3 — Badge
<StatusBadge status="ACTIVE" /> is nested inside Amina's <CustomerCard>,
right after her name heading.

## Step 4 — No runtime
This exercise is paper-only — no Vite app was created.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.