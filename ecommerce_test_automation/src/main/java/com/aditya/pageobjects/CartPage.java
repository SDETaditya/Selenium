package com.aditya.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aditya.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkout;

    public boolean isProductIsDisplayedOnCartPage(String productName) {
        return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));

    }

    public CheckoutPage goToCheckout(){
        checkout.click();
        return new CheckoutPage(driver);
    }

}
