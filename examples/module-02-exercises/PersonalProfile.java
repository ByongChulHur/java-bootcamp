import java.util.Scanner;

public class PersonalProfile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        String age = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("Hobby: ");
        String hobby = sc.nextLine();

        System.out.println();
        System.out.printf("%-10s | %-20s%n", "Field", "Value");
        System.out.println("-----------|---------------");
        System.out.printf("%-10s | %-20s%n", "Name", name);
        System.out.printf("%-10s | %-20s%n", "Age", age);
        System.out.printf("%-10s | %-20s%n", "City", city);
        System.out.printf("%-10s | %-20s%n", "Hobby", hobby);

        sc.close();
    }
}