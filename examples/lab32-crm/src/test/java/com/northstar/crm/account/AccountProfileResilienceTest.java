package com.northstar.crm.account;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
class AccountProfileResilienceTest {

  @RegisterExtension
  static WireMockExtension wireMock = WireMockExtension.newInstance()
          .options(wireMockConfig().port(8089))
          .build();

  @DynamicPropertySource
  static void accountApiBaseUrl(DynamicPropertyRegistry registry) {
    registry.add("account.api.base-url", () -> "http://localhost:8089");
  }

  @Autowired
  private AccountProfileService service;

  @Autowired
  private CircuitBreakerRegistry circuitBreakerRegistry;

  @BeforeEach
  void resetCircuitBreaker() {
    circuitBreakerRegistry.circuitBreaker("accountProfile").reset();
  }

  @Test
  void healthyCall_returnsAvailable() {
    wireMock.stubFor(get("/accounts/CUS-1001/summary")
            .willReturn(okJson("""
            {"customerId":"CUS-1001","available":true,"note":"ok"}
            """)));

    AccountSummary result = service.find("CUS-1001").join();

    assertThat(result.customerId()).isEqualTo("CUS-1001");
    assertThat(result.available()).isTrue();
  }

  @Test
  void openCircuit_failsFastWithoutHittingStub() {
    wireMock.stubFor(get("/accounts/CUS-1001/summary")
            .willReturn(aResponse().withStatus(503)));

    for (int i = 0; i < 4; i++) {
      service.find("CUS-1001").join();
    }

    wireMock.resetRequests();

    AccountSummary result = service.find("CUS-1001").join();

    assertThat(result.available()).isFalse();
    wireMock.verify(0, getRequestedFor(urlEqualTo("/accounts/CUS-1001/summary")));
  }

  @Test
  void timeout_returnsUnavailableFallback() {
    wireMock.stubFor(get("/accounts/CUS-1001/summary")
            .willReturn(okJson("""
            {"customerId":"CUS-1001","available":true,"note":"ok"}
            """).withFixedDelay(3000)));

    long start = System.currentTimeMillis();
    AccountSummary result = service.find("CUS-1001").join();
    long elapsedMs = System.currentTimeMillis() - start;

    assertThat(result.available()).isFalse();
    assertThat(elapsedMs).isLessThan(2500);
  }
}