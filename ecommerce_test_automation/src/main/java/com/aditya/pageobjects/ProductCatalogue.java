package com.aditya.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aditya.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
    WebDriver driver;
    
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    public List<WebElement> getProductList(){
        waitForElementToAppear(By.cssSelector(".mb-3"));
        return products;
    }
    
    public WebElement findProductByName(String productName){
        return getProductList()
                .stream()
                .filter(product -> product.findElement(By.cssSelector("b"))
                        .getText()
                        .equals(productName))
                .findFirst().orElse(null);
    }

    public void addProductToCart(String productName){
        findProductByName(productName).findElement(By.cssSelector(".card-body button:last-of-type")).click();
        waitForElementToAppear(By.cssSelector("#toast-container"));
    }
}
