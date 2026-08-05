package com.northstar.crm.service;

import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.CustomerRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer, String correlationId) {
        MDC.put("cust", customer.getCustomerId());
        MDC.put("op", "create");

        try {
            log.info("Creating customer");

            if (customer.getCustomerId() == null || customer.getCustomerId().isBlank()) {
                throw new IllegalArgumentException("customerId required [" + correlationId + "]");
            }

            if (repository.findById(customer.getCustomerId()).isPresent()) {
                throw new DuplicateCustomerException(customer.getCustomerId());
            }

            Customer saved = repository.save(customer);
            log.info("Customer created status={}", saved.getStatus());
            return saved;

        } catch (DuplicateCustomerException e) {
            log.warn("Create rejected reason=duplicate");
            throw e;
        } catch (IllegalArgumentException e) {
            log.warn("Create rejected reason=invalid_input");
            throw e;
        } catch (Exception e) {
            log.error("Create failed", e);
            throw e;
        }
    }

    public Optional<Customer> findById(String customerId) {
        MDC.put("cust", customerId);
        MDC.put("op", "get");

        Optional<Customer> result = repository.findById(customerId);
        log.info("Customer lookup found={}", result.isPresent());
        return result;
    }
}