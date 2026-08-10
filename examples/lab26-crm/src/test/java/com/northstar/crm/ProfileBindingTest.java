package com.northstar.crm;

import com.northstar.crm.config.NorthstarIntegrationProperties;
import com.northstar.crm.model.Customer;
import com.northstar.crm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ProfileBindingTest {

    @Autowired
    private NorthstarIntegrationProperties properties;

    @Autowired
    private CustomerService customerService;

    @Test
    void bindsTestProfilePropertiesAndKeepsCrmFixtures() {
        assertThat(properties.getConnectTimeoutMs()).isEqualTo(100);

        assertThat(properties.getApiBaseUrl()).isEqualTo("http://localhost:9090");

        Customer amina = customerService.get("CUS-1001");
        assertThat(amina.getName()).isEqualTo("Amina Khan");
    }
}