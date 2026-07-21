import java.util.Scanner;

public class CircleArea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Radius: ");
        double r = Double.parseDouble(sc.nextLine());
        double area = Math.PI * r * r;
        System.out.printf("Area: %.2f%n", area);   // %.2f → two decimal places
        sc.close();
    }
}