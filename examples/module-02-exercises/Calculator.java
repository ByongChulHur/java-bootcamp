import java.util.Scanner;
public class Calculator {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("First Number: ");
        double val_1 = Double.parseDouble(scanner.nextLine());
        System.out.print("Second Number: ");
        double val_2 = Double.parseDouble(scanner.nextLine());

        System.out.printf("Sum: %.2f%n", val_1+val_2);
        System.out.printf("Difference: %.2f%n", val_1 - val_2);
        System.out.printf("Product: %.2f%n", val_1 * val_2);
        System.out.printf("Quotient: %.2f%n", val_1 / val_2);
    }
}