public class MethodsDemo {

    public static int square(int a) {
        return a * a;
    }
    public static double square(double a){
        return a *= a; //Just for fun testing new stuff we learned today
    }
    public static void main(String[] args) {
        //This is basically doing method overloading
        int intResult = square(4);          // calls the int version
        double doubleResult = square(2.5);  // calls the double version — compiler picks by argument type

        System.out.println("square(4) = " + intResult);
        System.out.println("square(2.5) = " + doubleResult);
    }
}