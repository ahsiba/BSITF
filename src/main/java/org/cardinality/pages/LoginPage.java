package org.cardinality.pages;

import org.cardinality.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;

public class LoginPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private static final String USERNAME_BOX = "//input[@name = 'Username']";
    private static final String PASSWORD_BOX = "//input[@name = 'Password']";
    private static final String LOGIN_BUTTON = "//button[@type= 'submit']";
    private static final String WELCOME_BSITF_SEARCH_HEADER = "//div[@id = 'dashboardItems']//img[@alt='profile-pic']";
    private static final String Email_Input="//input[@id='email']";
    private static final String Enter_Password="//input[@id='password']";
    private static final String Login_Button="//button[contains(text(), 'Log In')]";
    private static final String Forgot_Password="//a[contains(text(), 'Forgot Password?')]";
    private static final String Register="//a[contains(text(), 'Register')]";
    private static final String View_Password_Value="//input[@id='password']/following-sibling::i[contains(@class, 'fa-eye')]"; 
    private static final String emailAddressIp="//input[@id='emailforconfirm']";
    private static final String sendBtn="//button[contains(., 'Send')]";

    public LoginPage(Page page) {
        super(page);
    }

    public boolean login(String username, String password) {
        logger.info("Filling in the username.");
        if (username == null || password == null) {
            logger.error("Username or password envionment variables are not set");
            return false;
        }
        page.fill(Email_Input, username);
        page.fill(Enter_Password, password);
        page.click(Login_Button);
        logger.info("Waiting for the welcome header to be visible.");
        // Retry mechanism for visibility check
        boolean isWelcomeHeaderVisible = false;
        for (int i = 0; i < 1; i++) {
            try {
                isWelcomeHeaderVisible = page.waitForSelector(WELCOME_BSITF_SEARCH_HEADER,
                        new Page.WaitForSelectorOptions().setTimeout(60000)).isVisible();
                if (isWelcomeHeaderVisible)
                    break;
            } catch (TimeoutError e) {
                logger.warn("Retrying due to timeout...");
            }
        }

        if (!isWelcomeHeaderVisible) {
            logger.error("Login failed, welcome header is not visible.");
            return false;
        }

        page.waitForSelector(WELCOME_BSITF_SEARCH_HEADER).isVisible();
        boolean isEnabled = page.isEnabled("input");
        if (isEnabled) {
            logger.info("Login successful, input fields are enabled.");
        } else {
            logger.error("Login failed, input fields are disabled.");
        }
        return isEnabled;
    }

}
