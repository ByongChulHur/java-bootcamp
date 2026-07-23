package com.academy.bank;

public class Transaction {
    private String transactionId;
    private double amount;
    private String type;
    private String date;
    private String accountNumber;

    public Transaction(String transactionId, double amount, String type, String date, String accountNumber) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.accountNumber = accountNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void display() {
        System.out.printf("Transaction %s: %s of %.2f on account %s (%s)%n",
                transactionId, type, amount, accountNumber, date);
    }
}