import java.util.Scanner;

public class DecisionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Score (0-100): ");
        int score = Integer.parseInt(scanner.nextLine());

        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");
        } else if (score >= 70) {
            System.out.println("Grade: C");
        } else {
            System.out.println("Grade: F");
        }

        System.out.print("Day number (1-7): ");
        int day = Integer.parseInt(scanner.nextLine());

        // switch: jump straight to the matching case, no chained comparisons
        switch (day) {
            case 1 : System.out.println("Monday"); break;
            case 2 : System.out.println("Tuesday");break;
            case 3 : System.out.println("Wednesday");break;
            case 4 : System.out.println("Thursday");break;
            case 5 : System.out.println("Friday");break;
            case 6 : System.out.println("Saturday");break;
            case 7 : System.out.println("Sunday");break;
            default : System.out.println("Not a valid day");
        }

        scanner.close();
    }
}