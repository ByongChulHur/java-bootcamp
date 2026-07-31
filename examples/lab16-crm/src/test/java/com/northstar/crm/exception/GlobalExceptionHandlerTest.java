package com.northstar.crm.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void mapsNotFound() {
        var handler = new GlobalExceptionHandler();
        var err = handler.fromBusiness(
                BusinessException.notFound("CUS-9999", "lab-request-001"));
        assertEquals(404, err.getStatus());
        assertEquals("lab-request-001", err.getCorrelationId());
    }

    @Test
    void mapsValidationEmail() {
        var validator = jakarta.validation.Validation.buildDefaultValidatorFactory().getValidator();
        var dto = new com.northstar.crm.dto.CustomerRequestDTO("CUS-3001", "Test User", "not-an-email", "PROSPECT");
        var violations = validator.validate(dto);
        var err = handler.fromValidation(violations, "lab-request-001");
        assertEquals(400, err.getStatus());
        assertTrue(err.getErrors().containsKey("email"));
    }

    @Test
    void mapsConflict() {
        var err = handler.fromBusiness(
                BusinessException.conflict("illegal status transition ACTIVE -> PROSPECT", "lab-request-001"));
        assertEquals(409, err.getStatus());
    }
}
