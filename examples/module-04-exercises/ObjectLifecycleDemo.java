public class ObjectLifecycleDemo {
    static class Person {
        final String name;

        Person(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Person first = new Person("Aman"); // create + reference
        Person alias = first; //Create another that points to same thing.

        System.out.println(
                "Same object: " + (first == alias));

        first = null;  //now we set it as null so it is no longer reference(object remains reachable through alias)
        System.out.println(
                "Still reachable through alias: " + alias.name);

        alias = null;  // same no longer reference for alias too(no strong references remain)
        System.out.println(
                "No strong references remain; object is GC-eligible.");

        System.out.println("GC requested, not guaranteed.");
    }
}