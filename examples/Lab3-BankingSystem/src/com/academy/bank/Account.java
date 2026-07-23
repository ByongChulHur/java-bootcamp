package com.academy.bank;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private Customer customer;

    protected Account(String accountNumber, double balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double calculateCharges() {
        return 0.0;
    }

    public String getAccountType() {
        return "Account";
    }

    public double calculateInterest() {
        return 0.0;
    }

    public abstract void displayAccount();

    public void deposit(double amount){
        if (amount <= 0){
            System.out.println("Invalid - Non Positive amount");
            return;
        }
        double cur = getBalance();
        setBalance(amount+cur);
    }
    public boolean withdraw(double amount){
        double cur = getBalance();
        double total = amount + calculateCharges();
        if (cur < total){
            System.out.println("Invalid - Not enough balance");
            return false;
        }
        setBalance(cur - total);
        return true;
    }
}