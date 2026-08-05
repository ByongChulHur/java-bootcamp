package com.northstar.crm.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/** Page Object — locate via data-testid only. */
public class CustomerFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By customerId = By.cssSelector("[data-testid='customer-id']");
    private final By fullName = By.cssSelector("[data-testid='full-name']");
    private final By email = By.cssSelector("[data-testid='email']");
    private final By status = By.cssSelector("[data-testid='status']");
    private final By submit = By.cssSelector("[data-testid='submit-customer']");
    private final By result = By.cssSelector("[data-testid='create-result']");

    public CustomerFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public CustomerFormPage open(String baseUrl) {
        driver.get(baseUrl + "/customers.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(customerId));
        return this;
    }

    public void fill(String id, String name, String email, String status) {
        driver.findElement(customerId).clear();
        driver.findElement(customerId).sendKeys(id);

        driver.findElement(fullName).clear();
        driver.findElement(fullName).sendKeys(name);

        driver.findElement(this.email).clear();
        driver.findElement(this.email).sendKeys(email);

        driver.findElement(this.status).clear();
        driver.findElement(this.status).sendKeys(status);
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
    }

    public String resultText() {
        wait.until(d -> !driver.findElement(result).getText().isBlank());
        return driver.findElement(result).getText();
    }
}