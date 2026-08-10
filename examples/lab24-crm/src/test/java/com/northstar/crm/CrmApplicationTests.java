package com.northstar.crm;

import com.northstar.crm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CrmApplicationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    void contextLoadsAndSeedDataVisible() {
        assertThat(customerService.get("CUS-1001").getName()).isEqualTo("Amina Khan");
    }
}