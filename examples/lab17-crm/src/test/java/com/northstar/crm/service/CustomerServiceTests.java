package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTests {
    DefaultCustomerService service;

    @BeforeEach
    void setUp() {
        InMemoryCustomerRepository repository = new InMemoryCustomerRepository();
        CustomerValidator validator = new CustomerValidator(repository);
        service = new DefaultCustomerService(repository, validator);
    }

    @Test
    void addAndActivateRaviHappyPath() {
        service.addCustomer(Customer.amina());
        service.addCustomer(Customer.ravi());
        Customer updated = service.changeStatus("CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");
        assertEquals(CustomerStatus.ACTIVE, updated.getStatus());
        assertEquals("CUS-1002", updated.getCustomerId());
    }

    @Test
    void duplicateIdThrowsConflict() {
        service.addCustomer(Customer.amina());
        BusinessException exception = assertThrows(BusinessException.class, () -> service.addCustomer(Customer.amina()));
        assertEquals("BUSINESS_CONFLICT", exception.getCode());
    }

    @Test
    void duplicateEmailThrowsConflict() {
        service.addCustomer(Customer.amina()); // amina.khan@example.com
        Customer duplicateEmailCustomer = new Customer("CUS-2003", "Fake Person", Customer.amina().getEmail(), null, CustomerStatus.PROSPECT, java.time.LocalDateTime.now());
        BusinessException exception = assertThrows(BusinessException.class, () -> service.addCustomer(duplicateEmailCustomer));
        assertEquals("BUSINESS_CONFLICT", exception.getCode());
    }

    @Test
    void listAllReturnsBothCustomers() {
        service.addCustomer(Customer.amina());
        service.addCustomer(Customer.ravi());
        List<Customer> all = service.listAll();
        assertEquals(2, all.size());
        assertTrue(all.stream().anyMatch(c -> "CUS-1001".equals(c.getCustomerId())));
        assertTrue(all.stream().anyMatch(c -> "CUS-1002".equals(c.getCustomerId())));
    }

    @Test
    void illegalTransitionThrowsConflict() {
        service.addCustomer(Customer.amina());
        BusinessException exception = assertThrows(BusinessException.class, () -> service.changeStatus("CUS-1001", CustomerStatus.PROSPECT, "lab-request-001"));
        assertEquals("BUSINESS_CONFLICT", exception.getCode());
        assertEquals("lab-request-001", exception.getCorrelationId());
        Customer stillAmina = service.findById("CUS-1001").orElseThrow();
        assertEquals(CustomerStatus.ACTIVE, stillAmina.getStatus());
    }

    @Test
    void missingCustomerThrowsNotFound() {
        BusinessException exception = assertThrows(BusinessException.class, () -> service.changeStatus("CUS-9999", CustomerStatus.ACTIVE, "lab-request-001"));
        assertEquals("CUSTOMER_NOT_FOUND", exception.getCode());
        assertEquals("lab-request-001", exception.getCorrelationId());
    }

}
