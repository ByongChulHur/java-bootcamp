lab10-001 — weak vs strong (entity)
- Date: 2026-07-28

- Weak prompt used: // customer class
- Output summary: Generated a class with wrong field names (id, name, status
  instead of customerId, fullName), status as plain String instead of
  CustomerStatus enum, and missing email/phone/createdAt fields entirely.
  No equals/hashCode.

- Strong prompt used: // Java entity class Customer in package com.northstar.crm.entity representing a Northstar CRM customer. 
Fields: customerId (String, format "CUS-1001"), fullName (String), email (String), phone (String), status (CustomerStatus enum: PROSPECT, ACTIVE, SUSPENDED, CLOSED), createdAt (LocalDateTime). 
No-args constructor, all-args constructor, getters and setters, equals/hashCode based only on customerId, toString.
- Output summary: After using strong prompt, it generated correct field names,status typed as CustomerStatus enum, and included all required fields.
- Decision: accept (strong version)
- Reason: Correct field names, type-safe status, and all required fields are present.

lab10-002 — weak vs strong (addCustomer)

Weak prompt used: // add a customer
Output summary: For the result for using weak prompt, the Copilot recommended a
generic `public static void main(String[] args)` method which is completely
unrelated to adding a customer. It had no idea what "customer" meant
without a Customer/CustomerService class nearby.

- Strong prompt used: // Method addCustomer(Customer customer) on CustomerService:
  reject if customerId is null/blank, reject if a customer with the same
  customerId already exists (throw IllegalStateException), otherwise store
  it in the in-memory list and return it.
- Output summary: After using strong prompt, it generated a method that correctly checks for null/blank customerId.
 However, there was one issue which is that Copilot used cusomterRepository.save(customer) which is just an 
- unfinished stub and calling this will crash.
- Decision: partial (keep validation, rewrite storage)
- Reason: Good validation, but wrong storage method yet, it used an unfinished
  dependency instead of the in-memory list the prompt asked for.

lab10-003 — human review checklist

#	Confirm	Your notes
1	Every import resolves against pom.xml deps actually present (no phantom JPA/Spring imports)	Pass
2	Business rules from the prompt appear in code (blank ID rejected, duplicate ID rejected, unknown ID rejected)—not only in comments	Pass
3	equals / hashCode based on customerId only	Pass / 
4	You could explain every line to a reviewer with Copilot turned off	Pass 
5	No hardcoded secrets, real customer PII, or inappropriate test data committed	Pass

Note: When Copilot generated code for first time, it called customerRepository.save(customer) which is just a stub and would crash. 
I rewrote it to use the in-memory list as the prompt asked for.

lab10-004 — AI risk awareness

1. What real customer data did you avoid typing into Chat, and what did you use instead (CUS-1001 / CUS-1002)?
  In the following labs, I avoided typing any sensitive customer data into chat, I used the placeholder customer IDs CUS-1001 and CUS-1002, which are fictional and safe to use in examples. 
  I also avoided using any real names, emails, or phone numbers, and instead used generic placeholders or fictional data.

2. If Copilot suggests a block that looks copied verbatim from a known library/article, what do you do before accepting?
  If the given code seems unique or seems long then before I just copy paste or use the given generated code, I first need to doubt and check the source
  if it is from a known library or article. I will check the code against known libraries, documentation, or articles to ensure that it is not plagiarized or violates any licensing terms.

3. What is your team’s rule for code Copilot generates that you do not fully understand?
If there is a code that I cannot understand, I will not use it right away. Instead of doing that I will try to ask Copilot to explain the code,
or I will research and read documentation to understand it better.

#Step 9 Failure Experiments — Lab 10

Case 1
Experiment: When I was creating addCustomer method, even I suggest to use in-memory list to store the customer,
Copilot still suggested to use customerRepository.save(customer) which is just a stub and would crash. I rewrote it to use the in-memory list as the prompt asked for.

- Observed: CustomerRepository does exist in the project, but it is
  still an unimplemented Lab 8 stub — calling it would throw an
  exception. Copilot defaulted to a familiar Repository pattern out
  of habit.
- Conclusion: Code that compiles isn't the same as code that actually
    works. Fixed by rewriting to use List.add() directly as required.

Case 2
- Experiment: Wrote deleteCustomer(String customerId) by hand, without
  accepting any Copilot suggestion:

  public boolean deleteCustomer(String customerId) {
  return customers.removeIf(c -> c.getCustomerId().equals(customerId));
  }

- Observed: Was able to complete it using the same patterns learned
  earlier (stream-based filtering, equals-based comparison) without
  needing AI assistance.
- Conclusion: Copilot speeds things up, but the task is still doable
  without it — a check against over-reliance.

Case 3
- Draft (not sent): "Generate a Customer with SSN 123-45-6789 as an
  example"
- Observed: Even a fake value in the same format as a real SSN is a
  bad habit to put into a prompt.
- Conclusion: Did not send this prompt. Continued using only the
  established safe fixture IDs, CUS-1001 / CUS-1002, instead.

Case 4
- Prompt: "Build the entire CRM service layer"
- Observed: Copilot changed to agent mode and then start reading over the entire projects such as
  service, entity, dto, and etc. After that it asked for my permission to run mvn clean compile command.
- Conclusion: As I expected, it was same as what lab warned me. Instead of prompting specific scope and just
  ask for build everything is hard to review entire file at once. The scoped one class at a time is much
  easier to review and understand.