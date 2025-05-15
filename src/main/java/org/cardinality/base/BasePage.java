package org.cardinality.base;

import java.util.Properties;

import org.cardinality.factory.BrowserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    private final BrowserFactory browserFactory = new BrowserFactory();
    protected Properties properties = browserFactory.initializeConfigProperties();
    protected Faker faker = new Faker();
    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    protected void waitForElement(String locator, String waitType) {
        int waitTime;
        switch (waitType.toUpperCase()) {
            case "SHORT_WAIT":
                waitTime = Integer.parseInt(properties.getProperty("SHORT_WAIT"));
                break;
            case "LONG_WAIT":
                waitTime = Integer.parseInt(properties.getProperty("LONG_WAIT"));
                break;
            case "DEFAULT_WAIT":
            default:
                waitTime = Integer.parseInt(properties.getProperty("DEFAULT_WAIT"));
                break;
        }
        if (locator == null || locator.isEmpty()) {
            logger.error("Locator cannot be null or empty");
            throw new IllegalArgumentException("Locator cannot be null or empty");
        }
        logger.info("Waiting for up to {} milliseconds for element '{}' to be visible", waitTime, locator);
        page.waitForSelector(locator,
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(waitTime));
        logger.info("Element '{}' is visible", locator);
    }

}
