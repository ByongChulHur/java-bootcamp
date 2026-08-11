package com.northstar.crm.api;

import com.northstar.crm.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
    List<ErrorResponse.FieldViolation> violations = ex.getBindingResult().getFieldErrors().stream()
            .map(fe -> new ErrorResponse.FieldViolation(fe.getField(), fe.getDefaultMessage()))
            .collect(Collectors.toList());

    ErrorResponse body = new ErrorResponse();
    body.setStatus(HttpStatus.BAD_REQUEST.value());
    body.setError("Bad Request");
    body.setMessage("Validation failed");
    body.setCorrelationId(correlationId(request));
    body.setViolations(violations);

    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(IllegalArgumentException ex, WebRequest request) {
    ErrorResponse body = new ErrorResponse();
    body.setStatus(HttpStatus.NOT_FOUND.value());
    body.setError("Not Found");
    body.setMessage(ex.getMessage());
    body.setCorrelationId(correlationId(request));
    body.setViolations(List.of());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ErrorResponse> handleConflict(IllegalStateException ex, WebRequest request) {
    ErrorResponse body = new ErrorResponse();
    body.setStatus(HttpStatus.CONFLICT.value());
    body.setError("Conflict");
    body.setMessage(ex.getMessage());
    body.setCorrelationId(correlationId(request));
    body.setViolations(List.of());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleSafe500(Exception ex, WebRequest request) {
    log.error("Unhandled exception, correlationId={}", correlationId(request), ex);

    ErrorResponse body = new ErrorResponse();
    body.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    body.setError("Internal Server Error");
    body.setMessage("Unexpected error");
    body.setCorrelationId(correlationId(request));
    body.setViolations(List.of());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }

  private String correlationId(WebRequest request) {
    String id = request.getHeader("X-Correlation-Id");
    return (id != null && !id.isBlank()) ? id : "lab-request-001";
  }
}