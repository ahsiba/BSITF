package org.cardinality.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.playwright.Page;
/**
 * Base Component class that provides common functionality for UI Components
 * This class encapsulates interactions with Playwright page elements through locators
 */
public class BaseComponent {

    protected String locator;
    protected Page page;
    private static final Logger logger = LoggerFactory.getLogger(BaseComponent.class);

    protected BaseComponent(Page page, String locator) {
        this.locator = locator;
        this.page = page;
    }

    public void click() {
        logger.debug("Clicking on element: {}", locator);
        try {
            page.click(locator);
        } catch (Exception e) {
            logger.error("Failed to click element with locator: {}", locator, e);
            throw e;
        }
    }

    public boolean isVisible() {
        logger.debug("Checking if element is visible: {}", locator);
        return page.isVisible(locator);
    }

    public boolean isEnabled() {
        logger.debug("Checking if element is enabled: {}", locator);
        return page.isEnabled(locator);
    }

    public String getValueFromElement() {
        logger.debug("Getting value from element: {} ", locator);
        return page.getAttribute(locator, "value");
    }

}
