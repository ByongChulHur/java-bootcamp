Reflection Question

Which prompt changed most between first attempt and final accepted version?
I think the most changed suggestion I saw was then I used addCustomer prompted. Since I used weak prompt,
it just generated unrelated weird main method. After I give it specific prompt, it generated correct method.

What was the most dangerous Copilot suggestion you saw, and how did you catch it?
I think the most dangerous suggestion I saw was then I used addCustomer prompted. Copilot generated following method
with using customerRepository.save() without checking if the following is useable. However since it was empty stub it will cause error.


What evidence would convince a skeptical tech lead you did not blindly accept AI output?
I will show the review notes file. I wrote the comparison between weak and strong prompt.
I wrote all the repository bug I found and fixed, and why I accepted or rejected the AI output.

How would review change if this code touched real (non-fictional) customer PII?
I will be a lot more careful with it. I will never use any real data in prompts or not even screenshots.

Which task was faster with Copilot, and which was slower once review time counted?
Getting all the boilerplate which Copilot finished it very fast. But catching and fixing the repository took
bit longer than I expected.

How does this lab connect to the Northstar CRM platform across Weeks 2–6?
This is basically the first time the Customer domain actually does
something meanwhile lab 8 and 9 were more focused on structure and build setup. Lab 11 will builds tests on top of this same
customerservice and later on it will gets a real repository and database connection.

What would you put in .github/copilot-instructions.md to prevent the JPA-annotation mistake?
Don't suggest @Entity, @Id, @Column, @Service, @Autowired or any javax/jakarta.persistence or org.springframework
imports unless I specifically ask.

What is the difference between “Copilot wrote this” and “I am responsible for this” professionally?
Copilot wrote it just means who typed it first. Me being responsible means if it breaks or has a security hole,
that's on me, not the AI.

(Forward look) How should Lab 11 treat AI-generated tests differently from AI-generated production code?
Probably need to be even more careful with tests, since a bad test can just quietly pass and hide a real bug.
