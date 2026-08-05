package com.northstar.crm.service;

public class DuplicateCustomerException extends RuntimeException {
    public DuplicateCustomerException(String customerId) {
        super("Customer already exists: " + customerId);
    }
}