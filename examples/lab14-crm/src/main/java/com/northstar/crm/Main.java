package com.northstar.crm;

import com.northstar.crm.api.CustomerApiFacade;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.dto.CustomerResponseDTO;
import com.northstar.crm.service.CustomerService;

public class Main {
    public static void main(String[] args) {
        CustomerApiFacade api = new CustomerApiFacade(new CustomerService());
        String correlationId = "lab-request-001";

        CustomerResponseDTO amina = api.create(
                new CustomerRequestDTO("CUS-1001", "Amina Khan", "amina.khan@example.com", "ACTIVE"),
                correlationId);
        System.out.println("Created: " + amina.getCustomerId() + " " + amina.getFullName() + " " + amina.getStatus());

        CustomerResponseDTO ravi = api.create(
                new CustomerRequestDTO("CUS-1002", "Ravi Singh", "ravi.singh@example.com", "PROSPECT"),
                correlationId);
        System.out.println("Created: " + ravi.getCustomerId() + " " + ravi.getFullName() + " " + ravi.getStatus());

        CustomerResponseDTO fetched = api.get("CUS-1001", correlationId);
        System.out.println("Fetched: " + fetched.getCustomerId() + " " + fetched.getFullName());

        try {
            api.create(new CustomerRequestDTO("CUS-9999", "Bad Email", "not-an-email", "PROSPECT"),
                    correlationId);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected failure: " + e.getMessage());
        }
    }
}