package com.northstar.crm.api;

import com.northstar.crm.model.Customer;
import com.northstar.crm.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customers;

    public CustomerController(CustomerService customers) {
        this.customers = customers;
    }

    @PostMapping
    public ResponseEntity<Customer> create(
            @RequestHeader(value = "X-Correlation-Id", required = false) String correlationId,
            @RequestBody Customer body) {
        String correlation = (correlationId != null) ? correlationId : "lab-request-001";
        try {
            Customer created = customers.create(body, correlation);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("X-Correlation-Id", correlation)
                    .body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .header("X-Correlation-Id", correlation)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable String id) {
        return customers.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
