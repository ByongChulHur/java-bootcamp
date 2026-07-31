package com.northstar.crm.exception;

import java.time.Instant;
import java.util.Map;

public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String correlationId;
    private Map<String, String> errors;

    public ErrorResponse(int status, String error, String message, String correlationId, Map<String, String> errors){
        this.timestamp = Instant.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
        this.correlationId = correlationId;
        this.errors = errors;
    }

    public String getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getCorrelationId() { return correlationId; }
    public Map<String, String> getErrors() { return errors; }
    // TODO: fields timestamp, status, error, message, correlationId, errors (always present, maybe empty)
    // TODO: constructor + getters
    // TODO: toJson() that always includes errors:{}

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"timestamp\":\"").append(timestamp).append("\",");
        sb.append("\"status\":").append(status).append(",");
        sb.append("\"error\":\"").append(error).append("\",");
        sb.append("\"message\":\"").append(message).append("\",");
        sb.append("\"correlationId\":\"").append(correlationId).append("\",");
        sb.append("\"errors\":{");
        int i = 0;
        for (Map.Entry<String, String> e : errors.entrySet()) {
            if (i++ > 0) sb.append(",");
            sb.append("\"").append(e.getKey()).append("\":\"").append(e.getValue()).append("\"");
        }
        sb.append("}}");
        return sb.toString();
    }
}
