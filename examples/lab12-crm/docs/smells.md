# Code smells — Lab 12

Catalog **≥8** smells from the messy baseline (`doStuff`). Tie each to CRM impact (CUS-1001).

| # | Smell | Location | Impact on CUS-1001 |
| - | ----- | -------- | ------------------ |
| 1 | Poor naming (`doStuff`, `data`) | `doStuff(String a, String b, String c, String d, String e)` | I can't tell what this method does just by name, and I don't know which param is Amina's id. Makes it slow to debug her lookup problem |
| 2 | Raw types | `List data = new ArrayList();` | No type check, so every time we read from `data` we have to cast it to `Customer` manually. Could break if wrong data type gets added |
| 3 | Long method / mixed responsibilities | `doStuff` (whole method) | Create and update logic are jammed into one method. If I fix update, I might accidentally break create, which is how Amina was added in the first place |
| 4 | Stringly-typed status | `e.equals("ACTIVE")` chain | If someone types `"Active"` instead of `"ACTIVE"` by mistake, Amina's status silently falls back to `PROSPECT` instead of `ACTIVE` |
| 5 | Incorrect equality (`==`) | `get()`: `x.getCustomerId() == id` | This is the actual bug — comparing strings with `==` means `get("CUS-1001")` can return `null` even when Amina really exists, just because the string came from a different object |
| 6 | Null as control flow | `return null;` on bad input/duplicate | If something goes wrong, it just returns `null` with no message. Whoever calls this has no idea why it failed |
| 7 | Side-effect logging | `System.out.println("bad")` / `"dup"` / `"ok " + a` | Just random print statements, no way to search logs for anything specific to `CUS-1001` |
| 8 | Magic `"UPDATE"` behavior | `if (b != null && b.contains("UPDATE"))` | If Amina's name ever happens to contain the word "UPDATE", her data gets changed automatically without anyone calling an update on purpose |