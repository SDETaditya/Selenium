package com.aditya.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aditya.pageobjects.CartPage;
import com.aditya.pageobjects.CheckoutPage;
import com.aditya.pageobjects.ConfirmationPage;
import com.aditya.pageobjects.ProductCatalogue;
import com.aditya.testcomponents.BaseTest;

public class StandaloneTestCopy extends BaseTest {

    @Test
    public void submitOredr() throws InterruptedException, IOException {
        String productName = "ZARA COAT 3";


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


    }
}