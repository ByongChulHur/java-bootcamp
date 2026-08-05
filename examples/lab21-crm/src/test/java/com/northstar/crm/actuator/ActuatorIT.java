package com.northstar.crm.actuator;

import com.northstar.crm.health.CrmReadinessIndicator;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActuatorIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    CrmReadinessIndicator readiness;

    @Test
    void healthAndProbesAreUp() {
        ResponseEntity<Map> health = rest.getForEntity("http://localhost:" + port + "/actuator/health", Map.class);
        assertTrue(health.getStatusCode().is2xxSuccessful());
        assertEquals("UP", health.getBody().get("status"));

        ResponseEntity<Map> liveness = rest.getForEntity("http://localhost:" + port + "/actuator/health/liveness", Map.class);
        assertTrue(liveness.getStatusCode().is2xxSuccessful());
        assertEquals("UP", liveness.getBody().get("status"));

        ResponseEntity<Map> readinessRes = rest.getForEntity("http://localhost:" + port + "/actuator/health/readiness", Map.class);
        assertTrue(readinessRes.getStatusCode().is2xxSuccessful());
        assertEquals("UP", readinessRes.getBody().get("status"));
    }

    @Test
    void readinessCanGoDownWhileLivenessStaysUp() {
        readiness.setReady(false);
        try {
            ResponseEntity<Map> readinessRes = rest.getForEntity("http://localhost:" + port + "/actuator/health/readiness", Map.class);
            assertNotEquals("UP", readinessRes.getBody().get("status"));

            ResponseEntity<Map> liveness = rest.getForEntity("http://localhost:" + port + "/actuator/health/liveness", Map.class);
            assertEquals("UP", liveness.getBody().get("status"));
        } finally {
            readiness.setReady(true);
        }
    }

    @Test
    void createMetricAppearsAfterTraffic() {
        String body = "{\"customerId\":\"CUS-2101\",\"fullName\":\"Metric User\",\"email\":\"metric@example.com\",\"status\":\"PROSPECT\"}";
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-Correlation-Id", "lab-request-001");
        rest.postForEntity("http://localhost:" + port + "/api/customers",
                new org.springframework.http.HttpEntity<>(body, headers), String.class);

        ResponseEntity<Map> metrics = rest.getForEntity(
                "http://localhost:" + port + "/actuator/metrics/crm.customer.create", Map.class);
        assertTrue(metrics.getStatusCode().is2xxSuccessful());
    }
}