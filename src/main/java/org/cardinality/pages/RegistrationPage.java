// src/main/java/org/cardinality/pages/RegistrationPage.java
package org.cardinality.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.cardinality.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationPage extends BasePage {
    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationPage.class);

    // locators
    private static final String URL = "https://your-app-url.com/register";
    private static final String FIRST_NAME     = "//input[@id='prodiderFirstName']";
    private static final String MIDDLE_NAME    = "//input[@id='middlename']";
    private static final String LAST_NAME      = "//input[@id='lastname']";
    private static final String GENDER_DROPDWN = "(//div[@aria-label='dropdown trigger'])[1]";
    private static final String GENDER_OPTION  = "//li[@aria-label='%s']";
    private static final String DOB            = "//input[@id='dob']";
    private static final String EMAIL          = "//input[@id='email']";
    private static final String PHONE          = "//input[@placeholder='Enter Applicant Daytime Phone']";
    private static final String SSN            = "//input[@id='ssn']";
    private static final String ADDRESS        = "//input[@placeholder='Enter Mailing Address']";
    private static final String CITY           = "//input[@id='city']";
    private static final String COUNTY         = "//input[@id='countycd']";
    private static final String ZIP            = "//input[@placeholder='Enter ZIP Code']";
    private static final String REGISTER_BTN   = "//button[normalize-space()='Register']";
    private static final String BACK_TO_LOGIN  = "//a[normalize-space()='Back to Login']";
    private static final String NEWREGISTER_BTN = "//a[contains(text(), 'Register')]";
    private static final String FORGOT_PASSWORD = "//a[contains(text(), 'Forgot Password?')]";
    private static final String FORGOT_EMAIL_IP = "//input[@id='emailforconfirm']";
    private static final String SEND_BTN = "//button[contains(., 'Send')]";


    public RegistrationPage(Page page) {
        super(page);
        this.page = page;
    }

    public void fillRegistrationForm(
            String firstName,
            String middleName,
            String lastName,
            String gender,
            String dob,
            String emailAddr,
            String daytimePhone,
            String ssnValue,
            String mailingAddress,
            String cityName,
            String countyName,
            String zipCode
    ) {
        try {
            logger.info("Filling out registration form");

            Locator el;

            el = page.locator(FIRST_NAME);
            waitForElement("xpath=" + FIRST_NAME, "DEFAULT_WAIT");
            el.fill(firstName);

            el = page.locator(MIDDLE_NAME);
            waitForElement("xpath=" + MIDDLE_NAME, "DEFAULT_WAIT");
            el.fill(middleName);

            el = page.locator(LAST_NAME);
            waitForElement("xpath=" + LAST_NAME, "DEFAULT_WAIT");
            el.fill(lastName);

            // gender
            page.locator(GENDER_DROPDWN).click();
            page.locator(String.format(GENDER_OPTION, gender)).click();

            el = page.locator(DOB);
            waitForElement("xpath=" + DOB, "DEFAULT_WAIT");
            el.fill(dob);

            el = page.locator(EMAIL);
            waitForElement("xpath=" + EMAIL, "DEFAULT_WAIT");
            el.fill(emailAddr);

            el = page.locator(PHONE);
            waitForElement("xpath=" + PHONE, "DEFAULT_WAIT");
            el.fill(daytimePhone);

            el = page.locator(SSN);
            waitForElement("xpath=" + SSN, "DEFAULT_WAIT");
            el.fill(ssnValue);

            el = page.locator(ADDRESS);
            waitForElement("xpath=" + ADDRESS, "DEFAULT_WAIT");
            el.fill(mailingAddress);

            el = page.locator(CITY);
            waitForElement("xpath=" + CITY, "DEFAULT_WAIT");
            el.fill(cityName);

            el = page.locator(COUNTY);
            waitForElement("xpath=" + COUNTY, "DEFAULT_WAIT");
            el.fill(countyName);

            el = page.locator(ZIP);
            waitForElement("xpath=" + ZIP, "DEFAULT_WAIT");
            el.fill(zipCode);

            logger.info("All fields filled");
        } catch (PlaywrightException e) {
            logger.error("Error while filling registration form: {}", e.getMessage());
            throw e;
        }
    }

    public void submit() {
        try {
            logger.info("Submitting registration form");
            waitForElement("xpath=" + FORGOT_PASSWORD, "DEFAULT_WAIT");
            page.click("xpath=" + FORGOT_PASSWORD);
        } catch (PlaywrightException e) {
            logger.error("Error while clicking forgot password: {}", e.getMessage());
            throw e;
        }
      
    }

     public void ClickRegister() {
        try {
            logger.info("Navigating to registration page");
            page.navigate(URL);
        } catch (PlaywrightException e) {
            logger.error("Error while navigating to registration page: {}", e.getMessage());
            throw e;
        }
  
    }

    public boolean isBackToLoginVisible() {
        try {
            logger.info("Checking if 'Back to Login' link is visible");
            waitForElement("xpath=" + BACK_TO_LOGIN, "DEFAULT_WAIT");
            return true;
        } catch (PlaywrightException e) {
            logger.error("Error while checking 'Back to Login' link visibility: {}", e.getMessage());
            return false;
        }
  
    }

public void forgotAndResetPassword(String email) {
    try {
        logger.info("Initiating Forgot Password process.");

        // Click on the "Forgot Password" link
        logger.info("Clicking Forgot Password link.");
        waitForElement("xpath=" + FORGOT_PASSWORD, "DEFAULT_WAIT");
        page.click("xpath=" + FORGOT_PASSWORD);

        // Wait for the email input field to be visible on the redirected page
        logger.info("Redirected to Forgot Password page. Entering email and sending reset password request.");
        waitForElement("xpath=" + FORGOT_EMAIL_IP, "DEFAULT_WAIT");

        // Fill in the email address
        Locator emailInput = page.locator(FORGOT_EMAIL_IP);
        emailInput.fill(email);

        // Click the "Send" button
        Locator sendButton = page.locator(SEND_BTN);
        sendButton.click();

        logger.info("Reset password request sent successfully.");
    } catch (PlaywrightException e) {
        logger.error("Error during Forgot Password process: {}", e.getMessage());
        throw e;
    }
}
}
