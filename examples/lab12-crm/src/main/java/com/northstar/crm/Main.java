package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.service.CustomerService;

public class Main {
    public static void main(String[] args) {
        CustomerService svc = new CustomerService();

        Customer amina = svc.createCustomer("CUS-1001", "Amina Khan", "amina.khan@example.com", null, CustomerStatus.ACTIVE);
        System.out.println("Created: " + amina);

        Customer ravi = svc.createCustomer("CUS-1002", "Ravi Singh", "ravi.singh@example.com", null, CustomerStatus.PROSPECT);
        System.out.println("Created: " + ravi);

        System.out.println("Fetched CUS-1001: " + svc.getCustomer("CUS-1001"));

        Customer updatedRavi = svc.updateStatus("CUS-1002", CustomerStatus.ACTIVE);
        System.out.println("Updated CUS-1002 status: " + updatedRavi);

        try {
            svc.createCustomer("CUS-1001", "Duplicate Attempt", "dup@example.com", null, CustomerStatus.ACTIVE);
        } catch (IllegalStateException e) {
            System.out.println("Expected duplicate failure: " + e.getMessage());
        }

        try {
            svc.getCustomer("CUS-9999");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected not-found failure: " + e.getMessage());
        }

    }
}