Question 1.
Stack is data storage area where it saves all the local variables and references that point to the heap. 
Stack is fast because it only stores small things like primitive value and address.
While heap is where the actual data of objects gets saved, and it is bigger space compare to stack.
Also as it goes in the address of stack goes down while heap goes up.

Question 2.
Based on what I learned during module 4, locals are stored on the stack because they are short lived and their size is known in advance.
Each local variables are need when method is running which means that once its runned it no longer needed.

Question 3.
Objects are stored on the heap and this is because their lifetime is not known and their size not always known in advance which is also known ad dynamic.

Question 4.
The objects are GC - eligible when the moment no live reference can reach it anymore.

Question 5
No. it is only a request to the JVM, not a command.

Question 6
Based on the lab then the leak was caused by a static final field (LEAK_HOLDER) holding a reference to a list that Employees were continuously added to but never removed from.
However for general cases, leak happened because objects were kept in a collection that was continuously added to but never cleared.

Question 7
The fix used a local list instead of a static one, and explicitly called .clear() then set the reference to null before triggering GC.

Question 8
WeakReferences let the GC reclaim an object even while something is still technically "holding onto" it, as long as no strong reference remains.

Question 9
When the Heap runs out of space and the JVM cannot free enough memory or grow the heap further, 
it throws OutOfMemoryError: Java heap space and the program crashes.

Question 10
I would prefer to us VisualVM

Question 11
If a future CRM service kept a static, unbounded cache then it will repeat exactly the pattern from memoryLeakDemo.
