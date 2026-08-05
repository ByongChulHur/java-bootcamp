package com.northstar.crm.ui;

import com.northstar.crm.ui.pages.CustomerFormPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerUiIT {

    @LocalServerPort
    int port;

    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=1280,900");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void quit() {
        if (driver != null) driver.quit();
    }

    @Test
    void createCustomerViaUi() {
        String baseUrl = "http://localhost:" + port;
        CustomerFormPage page = new CustomerFormPage(driver);

        page.open(baseUrl);
        page.fill("CUS-2001", "Test User", "test.user@example.com", "PROSPECT");
        page.submit();

        assertTrue(page.resultText().contains("CUS-2001"));
    }

    @Test
    void blankNameShowsValidationMessage() {
        String baseUrl = "http://localhost:" + port;
        CustomerFormPage page = new CustomerFormPage(driver);

        page.open(baseUrl);
        page.fill("CUS-2002", "", "blank@example.com", "PROSPECT");
        page.submit();

        assertTrue(page.resultText().toLowerCase().contains("full name"));
    }
}
