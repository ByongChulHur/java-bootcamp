package com.northstar.crm.integration;

import com.northstar.crm.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerApiIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Test
    void getAminaReturns200() {
        ResponseEntity<Customer> response = rest.getForEntity("/api/customers/CUS-1001", Customer.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("CUS-1001", response.getBody().getCustomerId());
    }
    @Test
    void createEchoesCorrelationHeader() {
        var headers = new HttpHeaders();
        headers.set("X-Correlation-Id", "lab-request-001");
        headers.setContentType(MediaType.APPLICATION_JSON);
        var body = """
    {"customerId":"CUS-2001","fullName":"Test User","email":"test.user@example.com","status":"PROSPECT"}
    """;
        var response = rest.exchange(
                "http://localhost:" + port + "/api/customers",
                HttpMethod.POST, new HttpEntity<>(body, headers), Customer.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("lab-request-001", response.getHeaders().getFirst("X-Correlation-Id"));
    }

    @Test
    void missingCustomerReturns404() {
        ResponseEntity<Customer> response = rest.getForEntity("/api/customers/CUS-9999", Customer.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
