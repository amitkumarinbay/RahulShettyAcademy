package javaMethod;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObject.LoginOR;


public class LoginMethod {

    private static final Logger logger = LogManager.getLogger(LoginMethod.class);

    /**
     * Launches the specified URL in the browser.
     *
     * @param driver WebDriver instance
     * @param url URL to be opened
     */
    public static void launchBrowser(WebDriver driver, String url) {
        try {
            driver.get(url);
            logger.info("Browser launched and navigated to: {}", url);
        } catch (TimeoutException e) {
            logger.error("Timeout occurred while loading the URL: {}", url, e);
        } catch (Exception e) {
            logger.error("Unexpected error while launching browser: {}", e.getMessage(), e);
        }
    }

    /**
     * Enters the provided email ID in the email input field.
     *
     * @param driver WebDriver instance
     * @param emailId Email ID to be entered
     */
    public static void enterEmailId(WebDriver driver, String emailId) {
        try {
            driver.findElement(LoginOR.EMAIL).sendKeys(emailId);
            logger.info("Entered email ID: {}", emailId);
        } catch (NoSuchElementException e) {
            logger.error("Email input field not found.", e);
        }
    }

    /**
     * Enters the provided password in the password input field.
     *
     * @param driver WebDriver instance
     * @param password Password to be entered
     */
    public static void enterPassword(WebDriver driver, String password) {
        try {
            driver.findElement(LoginOR.PASSWORD).sendKeys(password);
            logger.info("Entered password (hidden for security)");
        } catch (NoSuchElementException e) {
            logger.error("Password input field not found.", e);
        }
    }

    /**
     * Clicks the login button.
     *
     * @param driver WebDriver instance
     */
    public static void clickLogin(WebDriver driver) {
        try {
            driver.findElement(LoginOR.LOGIN).click();
            logger.info("Login button clicked.");
        } catch (NoSuchElementException e) {
            logger.error("Login button not found.", e);
        }
    }

    /**
     * Verifies if the user is successfully redirected to the dashboard URL.
     *
     * @param driver WebDriver instance
     */
    public static void verifyUrl(WebDriver driver) {
        String expectedUrl = "https://rahulshettyacademy.com/client/dashboard/dash";
        
        try {
            // Applying Explicit Wait for URL verification
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean isUrlMatched = wait.until(ExpectedConditions.urlToBe(expectedUrl));

            if (isUrlMatched) {
                logger.info("Login successful. Redirected to: {}", driver.getCurrentUrl());
            } else {
                logger.error("URL mismatch. Expected: {}, but found: {}", expectedUrl, driver.getCurrentUrl());
                Assert.fail("URL Verification Failed");
            }
        } catch (Exception e) {
            logger.error("Unexpected error during URL verification.", e);
            Assert.fail("Exception occurred during URL verification");
        }
    }

}
