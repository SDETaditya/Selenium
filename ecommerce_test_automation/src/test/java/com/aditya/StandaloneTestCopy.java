package com.aditya;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.aditya.pageobjects.CartPage;
import com.aditya.pageobjects.CheckoutPage;
import com.aditya.pageobjects.ConfirmationPage;
import com.aditya.pageobjects.LandingPage;
import com.aditya.pageobjects.ProductCatalogue;

public class StandaloneTestCopy {
    @SuppressWarnings({ "null" })
    public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";
        Map<String, Object> prefs = new HashMap<>();


        prefs.put("profile.password_manager_leak_detection", false);

        
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);


        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LandingPage landingPage = new LandingPage(driver);
        landingPage.gotTo();

        ProductCatalogue productCatalogue = landingPage.loginApplication("aditya@email.com", "Aditya@123");

        productCatalogue.addProductToCart(productName);

        Thread.sleep(2000);

        CartPage cartPage = productCatalogue.goToPage("cart", CartPage.class);

        Assert.assertTrue(cartPage.isProductIsDisplayedOnCartPage(productName));

        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.selectCountry("india");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMessage();

        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        driver.close();

    }
}