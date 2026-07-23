package com.academy.bank;

import java.util.Scanner;

public class BankService {
    private static final int MAX_CUSTOMERS = 50;
    private static final int MAX_ACCOUNTS = 100;
    private static final int MAX_TRANSACTIONS = 500;

    private final Customer[] customers = new Customer[MAX_CUSTOMERS];
    private final Account[] accounts = new Account[MAX_ACCOUNTS];
    private final Transaction[] transactions = new Transaction[MAX_TRANSACTIONS];

    private int customerCount = 0;
    private int accountCount = 0;
    private int transactionCount = 0;
    private int nextAccountNumber = 10001;
    private int nextTransactionNumber = 1;

    private final Scanner scanner;

    public BankService(Scanner scanner) {
        this.scanner = scanner;
    }

    public Customer findCustomer(String id){
        for (int i = 0; i < customerCount; i++){
            if (customers[i].getCustomerId().equals(id)){
                return customers[i];
            }
        }
        return null;
    }

    public void createCustomer(){
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();

        if (findCustomer(id) != null){
            System.out.println("The following ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(id, name, email, phone);
        customers[customerCount++] = customer;

        System.out.println("Customer Created Successfully.");
    }

    private Customer readExistingCustomer() {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        Customer customer = findCustomer(id);
        if (customer == null) {
            System.out.println("Customer not found.");
        }
        return customer;
    }
    public void createSavingsAccount(){
        Customer customer = readExistingCustomer();
        if (customer == null){
            System.out.println("The customer is does not exist.");
            return;
        }

        System.out.print("Enter Initial Balance: ");
        double balance = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Interest Rate: ");
        double rate = Double.parseDouble(scanner.nextLine());

        String accountNumber = String.valueOf(nextAccountNumber++);
        SavingsAccount account = new SavingsAccount(accountNumber, balance, customer, rate);
        accounts[accountCount++] = account;

        System.out.println("Savings Account Created.");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance : " + balance);
        System.out.println("Interest Rate : " + rate + "%");
    }

    public void createCurrentAccount(){
        Customer customer = readExistingCustomer();
        if (customer == null){
            System.out.println("The customer is does not exist.");
            return;
        }

        System.out.print("Enter Initial Balance: ");
        double balance = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Transaction Fee: ");
        double fee = Double.parseDouble(scanner.nextLine());

        String accountNumber = String.valueOf(nextAccountNumber++);
        CurrentAccount account = new CurrentAccount(accountNumber, balance, customer, fee);
        accounts[accountCount++] = account;

        System.out.println("Current Account Created.");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance : " + balance);
        System.out.println("Transaction Fee : " + fee);

    }

    public Account findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++){
            if (accounts[i].getAccountNumber().equals(accountNumber)){
                return accounts[i];
            }
        }
        return null;
    }

    private void recordTransaction(String accountNumber, String type, double amount) {
        String transactionId = "T" + nextTransactionNumber++;
        Transaction transaction = new Transaction(transactionId, amount, type, "2026-07-22", accountNumber);
        transactions[transactionCount++] = transaction;
    }

    public void deposit(){
        System.out.print("Enter Account Number: ");
        String accountNumber  = scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account == null){
            System.out.println("Account not Found.");
            return;
        }

        System.out.print("Enter Deposit Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        account.deposit(amount);
        recordTransaction(accountNumber, "deposit", amount);
        System.out.println("Balance Updated: " + account.getBalance());
    }

    public void withdraw(){
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account == null){
            System.out.println("Account Not Found.");
            return;
        }

        System.out.print("Enter Withdraw Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        boolean withresult = account.withdraw(amount);

        if (withresult == true){
            recordTransaction(accountNumber, "withdraw", amount);
            System.out.println("Succeed on withdraw");
            System.out.println("Balance Updated: " + account.getBalance());
            return;
        }else{
            System.out.println("Failed to withdraw");
        }
    }

    public void displayAccounts() {
        for (int i = 0; i < accountCount; i++){
            accounts[i].displayAccount();
        }
    }

    public void displayCustomers() {
        for (int i = 0; i < customerCount; i++){
            customers[i].display();
        }
    }
}