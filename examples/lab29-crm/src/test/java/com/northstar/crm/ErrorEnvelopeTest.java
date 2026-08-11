package com.northstar.crm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ErrorEnvelopeTest {

  @org.springframework.beans.factory.annotation.Autowired
  private MockMvc mockMvc;

  private String loginAndGetToken() throws Exception {
    String body = mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"agent1\",\"password\":\"agent1\"}"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
    // accessToken 값만 뽑아내는 아주 단순한 방법 (외부 JSON 라이브러리 없이)
    return body.replaceAll(".*\"accessToken\":\"([^\"]+)\".*", "$1");
  }

  @Test
  void validationReturns400Envelope() throws Exception {
    String token = loginAndGetToken();

    mockMvc.perform(post("/api/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + token)
                    .header("X-Correlation-Id", "lab-request-001")
                    .content("{\"id\":\"\",\"name\":\"\",\"email\":\"bad\",\"status\":\"ACTIVE\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.correlationId").value("lab-request-001"))
            .andExpect(jsonPath("$.violations").isArray());
  }

  @Test
  void missingCustomerReturns404Envelope() throws Exception {
    String token = loginAndGetToken();

    mockMvc.perform(get("/api/customers/CUS-9999")
                    .header("Authorization", "Bearer " + token)
                    .header("X-Correlation-Id", "lab-request-001"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.correlationId").value("lab-request-001"));
  }

  @Test
  void duplicateReturns409Envelope() throws Exception {
    String token = loginAndGetToken();

    mockMvc.perform(post("/api/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + token)
                    .header("X-Correlation-Id", "lab-request-001")
                    .content("{\"id\":\"CUS-1001\",\"name\":\"Amina Khan\",\"email\":\"amina.khan@example.com\",\"status\":\"ACTIVE\"}"))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.status").value(409));
  }

  @Test
  void securityStillRequiresToken() throws Exception {
    mockMvc.perform(get("/api/customers/CUS-1001"))
            .andExpect(status().isUnauthorized());
  }
}