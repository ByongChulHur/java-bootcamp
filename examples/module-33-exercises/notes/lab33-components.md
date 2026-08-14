# Lab 33 — Component Inventory

## Step 1 — Screen
Imagine a Customer list showing Amina and Ravi with status badges.

## Step 2 — Inventory
- App
- CustomerList
- CustomerCard
- StatusBadge
- PageHeader

## Step 3 — One responsibility
- App: Root shell; composes the dashboard
- CustomerList: Renders one card per customer
- CustomerCard: Displays a single customer's info
- StatusBadge: Shows a customer's status text
- PageHeader: Displays page title and actions

## Step 4 — Notes
Presentational components (StatusBadge, CustomerCard) only receive props and render UI.
CustomerList and App will later become stateful parents in Lab 34 when state is lifted up.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.