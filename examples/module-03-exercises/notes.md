# Banking domain notes

| Entity | Identity | Important attributes | Main responsibility |
| ------ | -------- | -------------------- | ------------------- |
| Customer | customerId | name, email, phone | Maintain customer profile |
| Account | accountNumber | owner, balance, accountType | Protect balance and perform deposits/withdrawals |
| Transaction | transactionId | account, type, amount, timestamp | Record one account operation |

## Relationships

- One Customer can own zero or more Accounts.
- One Account belongs to exactly one Customer.
- One Account can have many Transactions.
- One Transaction belongs to exactly one Account.

## Rules

- An account balance cannot be changed directly from outside Account.
- A deposit amount must be positive.
- A withdrawal cannot exceed the allowed balance.
- 
## Design decision

Account should decide whether a withdrawal is valid, not Main, because Account
owns the balance and is the only place that can guarantee its rules are never
broken. If Main handled validation instead, every part of the program that
touches an account would need to repeat the same checks, and any one of them
could get it wrong and corrupt the balance. Keeping Main as a thin coordinator
that only handles user interaction keeps the business rules in one place.
