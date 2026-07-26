For exercise 5
map produced a new list of proposed values; it did not modify the immutable Employee records in the source list.

For exercise 7
Why should department filtering happen before mapping to names?
In stream, before any mapping or filtering the Employee object is in it. If we did map first by running map(Employee::name) then only it lefts in stream will be
just name for example "Alice", "bob" then in this String type we do not have Department information. So We cannot figure it out its rather HR or IT department based
on the given information.