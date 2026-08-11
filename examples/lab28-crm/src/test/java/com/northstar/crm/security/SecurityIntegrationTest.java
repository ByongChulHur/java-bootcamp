package com.northstar.crm.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void customers_requireAuthentication() throws Exception {
        mockMvc.perform(get("/api/customers/CUS-1001"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void badToken_returns401() throws Exception {
        mockMvc.perform(get("/api/customers/CUS-1001")
                        .header("Authorization", "Bearer garbage-token"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void agent_canReadCustomer() throws Exception {
        String token = loginAndGetToken("agent1", "agent1");
        mockMvc.perform(get("/api/customers/CUS-1001")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void agent_forbiddenOnAdmin() throws Exception {
        String token = loginAndGetToken("agent1", "agent1");
        mockMvc.perform(get("/api/admin/ping")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    void admin_allowedOnAdmin() throws Exception {
        String token = loginAndGetToken("admin1", "admin1");
        mockMvc.perform(get("/api/admin/ping")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    private String loginAndGetToken(String username, String password) throws Exception {
        String body = objectMapper.writeValueAsString(Map.of("username", username, "password", password));
        String response = mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).get("accessToken").asText();
    }
}