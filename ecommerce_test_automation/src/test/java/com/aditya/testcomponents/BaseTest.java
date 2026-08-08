package com.aditya.testcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aditya.pageobjects.LandingPage;

public class BaseTest {
    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/com/aditya/resources/GlobalData.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {

            Map<String, Object> prefs = new HashMap<>();

            prefs.put("profile.password_manager_leak_detection", false);

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;

    }

    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        LandingPage landingPage = new LandingPage(driver);
        return landingPage;
    }
}
