package com.academy.bank;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService(scanner);

        while (true){
            System.out.println("=== Bank Menu ===");
            System.out.println("1. Create Customer");
            System.out.println("2. Create Savings Account");
            System.out.println("3. Create Current Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Display Accounts");
            System.out.println("7. Display Customers");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1:
                    bankService.createCustomer();
                    break;
                case 2:
                    bankService.createSavingsAccount();
                    break;
                case 3:
                    bankService.createCurrentAccount();
                    break;
                case 4:
                    bankService.deposit();
                    break;
                case 5:
                    bankService.withdraw();
                    break;
                case 6:
                    bankService.displayAccounts();
                    break;
                case 7:
                    bankService.displayCustomers();
                    break;
                case 8:
                    System.out.println("Thank You");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}