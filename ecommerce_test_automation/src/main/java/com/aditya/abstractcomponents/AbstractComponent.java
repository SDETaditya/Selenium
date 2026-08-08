package com.aditya.abstractcomponents;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aditya.pageobjects.CartPage;

public class AbstractComponent {
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }

   public <T> T goToPage(String pageName, Class<T> pageClass) {
    driver.findElement(
        By.cssSelector(String.format("[routerLink*='%s']", pageName))
    ).click();

    try {
        return pageClass.getConstructor(WebDriver.class).newInstance(driver);
    } catch (Exception e) {
        throw new RuntimeException("Unable to create page object", e);
    }
}

}
