package com.aditya.pageobjects;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aditya.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement message;

    public ProductCatalogue loginApplication(String email, String pass) {
        userEmail.sendKeys(email);
        password.sendKeys(pass);
        submit.click();
        return new ProductCatalogue(driver);
    }

    public void gotTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getMessage(){
        return message.getText();
    }
}
