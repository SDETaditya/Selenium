package com.aditya.pageobjects;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aditya.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement countryOption;

    @FindBy(css = ".action__submit")
    WebElement submitButton;


    public void selectCountry(String countryName) {
        Actions action = new Actions(driver);
        action.sendKeys(country, countryName).perform();
        waitForElementToAppear(By.cssSelector(".ta-results"));
        countryOption.click();
    }

    public ConfirmationPage submitOrder() {
        submitButton.click();
        return new ConfirmationPage(driver);

    }

}
