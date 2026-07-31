# Lab 15 — Layer Diagram

## Step 1 — Boxes

Draw three boxes: [API adapter], [CustomerService], [CustomerRepository].

## Step 2 — Arrow labels

[ API adapter ] --activate(CUS-1002)--> [ CustomerService ] --activate(id)--> [ CustomerRepository ]
^                                                                              |
|______________________________ Return to Customer ______________________________|
## Step 3 — Correlation

lab-request-001 gets generated once API adapter gets the first request. ID won't end here it goes to Service layer and the following id log remians in Service too,
This is to make easy to track the flows going through API->Service layer.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.