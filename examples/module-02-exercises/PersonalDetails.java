import java.util.Scanner;

public class PersonalDetails {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name_input = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter your city: ");
        String city = sc.nextLine();
        System.out.printf("Hello, %s! You are %d years old and live in %s.%n", name_input, age, city);
        sc.close();
    }
}