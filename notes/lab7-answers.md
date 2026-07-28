1. What is the difference between checked and unchecked exceptions?

So for the Checked exception is exception that compiler force "us" to handle, like IOException, and it happen because of external situation. However when we look at
Unchecked exception like NullPointerException does not need to be caught, and it mostly happen because of programmer mistake.

2. Why should custom exceptions be used?

One of the main reason why Custom exceptions should be used because it give more meaning about what actually went wrong,
instead of just generic Exception message.
For example, InsufficientFundsException can carry extra info like requested amount and available balance.

3. What is exception propagation?

Exception propagation is when exception is not caught in the method it happen, so it go up to the caller, 
and keep going up until some method catch it. Also it goes in stack order too. 
For example in our lab, Account throw it and it propagate up to ATMService boundary.

4. What is the purpose of finally?

Purpose of finally is to run cleanup code no matter exception happen or not,
like closing resource or printing "Returning to Main Menu." Because of this, we can be sure that important code always execute.

5. Why is try-with-resources preferred?

Try-with-resources is preferred because it close the resource automatically instead of close it manually with finally.
Also, it prevent resource leak even how exception occur during reading.

6. When should throw be used?

We use Throw when we want to signal a problem right now inside method body, like when amount is invalid.
It create the exception object and stop normal execution immediately.

7. When should throws be used?

we use throws when it used in method signature when method contain checked exception that it does not handle itself, so it pass the responsibility to the caller. 
For example, Account.withdraw() use throws to declare it may fail.

8. Why is logging important in enterprise applications?

Logging is important because it give record of what happened, when, and why, so developer can debug problem later without needing to reproduce it.
Also, log with context like accountId help identify exact cause faster.

9. What happens if an exception is not handled?

If exception is not handled, it propagate all the way up to main method, and if main also doesn't catch it, JVM terminate the program and print stack trace.
Because of this, program stop abnormally and rest of the code never execute.

10. How does proper exception handling improve software reliability?

Proper exception handling improve reliability because program can recover from failure instead of crashing completely.
For example, in our ATM, invalid amount or insufficient funds just show error message and return to menu instead of killing whole application.

11. (Forward look) How would a future CRM map domain exceptions to API errors using the same boundary-catch + log pattern?

I personally think CRM would throw domain exception like CustomerNotFoundException at service layer, and catch it at API boundary to 
convert into proper HTTP error response like 404. Also, it would log the exception detail same way we did with LoggerUtil,
but return only short safe message to the client.