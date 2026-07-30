package com.northstar.crm.dto;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.time.LocalDateTime;

public final class CustomerMapper {
    private CustomerMapper() {}

    public static Customer toEntity(CustomerRequestDTO req) {
        return new Customer(
                req.getCustomerId(),
                req.getFullName(),
                req.getEmail(),
                null,
                CustomerStatus.valueOf(req.getStatus()),
                LocalDateTime.now()
        );
    }

    public static CustomerResponseDTO toResponse(Customer entity) {
        return CustomerResponseDTO.of(
                entity.getCustomerId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getStatus().name(),
                entity.getCreatedAt(),
                null
        );
    }
}