package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private final Map<String, Customer> customersById = new HashMap<>();

    public Customer createCustomer(String customerId, String fullName, String email, String phone, CustomerStatus status) {
        // a=id b=name c=email d=phone e=status-as-string
        validateNotBlank("customerId", customerId);
        validateNotBlank("fullName", fullName);
        if (customersById.containsKey(customerId)) {
            throw new IllegalStateException("Customer already exists: " + customerId);
        }

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setStatus(status != null ? status : CustomerStatus.PROSPECT);
        customer.setCreatedAt(LocalDateTime.now());

        customersById.put(customerId, customer);
        return customer;
    }

    public Customer getCustomer(String customerId) {
        Customer found = customersById.get(customerId);
        if (found == null) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId + " correlationId=lab-request-001");
        }
        return found;
    }
    public Customer updateStatus(String customerId, CustomerStatus newStatus) {
        Customer customer = getCustomer(customerId); // reuse the not-found check
        customer.setStatus(newStatus);
        return customer;
    }

    private void validateNotBlank(String fieldName, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }

}
