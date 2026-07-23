
1. When main start, main frame get created first.
2. Person object is created with new keyword, and it go to heap. But the person variable in main is just reference, not the actual object.
3. When printPerson is called, second frame is created for printPerson.
4. Now both main frame and printPerson frame are holding reference to same Person object at same time.
5. After printPerson finish running, its frame is removed from stack.
6. name length of "Aman" is 4 letters, so nameLength = 4 inside printPerson frame.
7. After main finish, main frame also removed, and now nothing reference the Person object anymore, so it become eligible for GC.