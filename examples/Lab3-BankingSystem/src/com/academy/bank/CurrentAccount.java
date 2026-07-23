package com.academy.bank;

public class CurrentAccount extends Account implements Printable {
    private double transactionFee;

    public CurrentAccount(String accountNumber, double balance, Customer customer, double transactionFee) {
        super(accountNumber, balance, customer);
        this.transactionFee = transactionFee;
    }

    @Override
    public double calculateCharges() {
        return transactionFee;
    }

    @Override
    public void displayAccount() {
        System.out.printf("Type: %s, Number: %s, Customer: %s, Balance: %.2f, Fee: %.2f%n",
                getAccountType(), getAccountNumber(), getCustomer().getName(), getBalance(), transactionFee);
    }

    @Override
    public void printDetails() {
        displayAccount();
    }

    @Override
    public String getAccountType() {
        return "Current";
    }

}