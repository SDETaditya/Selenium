package com.aditya.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aditya.testcomponents.BaseTest;

public class ErrorValidationTest extends BaseTest {
    @Test
    public void incorrectPassTest() {

        landingPage.loginApplication("aditya@email.com", "Adity@123");
        Assert.assertEquals(landingPage.getMessage(), "Incorrect email or password");
    }

}
