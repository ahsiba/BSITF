package org.cardinality.components;

import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.microsoft.playwright.Page;

/**
 * Textbox component that wraps Playwright page interactions for text input
 * Extends BaseComponent to inherit common Web Element functionality
 */

public class TextBox extends BaseComponent {

    private static final Logger logger = LoggerFactory.getLogger(TextBox.class);

    public TextBox(Page page, String locator) {
        super(page, locator);
    }

    /**
     * Fill a text input with the specified value
     *
     * @param value      The text to enter into the element
     * @param clear      Whether to clear the field before entering text
     * @param pressEnter pressEnter whether to press Enter after filling
     */
    public void fill(String value, boolean clear, boolean pressEnter) {
        logger.info("Filling element: {}", locator);
        // Ensure element is visible before interacting
        try {
            page.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
            if (value == null) {
                logger.warn("Null value provided to fill method for {}", locator);
                value = "";
            }
            if (clear) {
                page.fill(locator, "");
            }

            page.fill(locator, value);
            // Optional Validation for critical fields
            String actualValue = page.inputValue(locator);
            if (!actualValue.equals(value)) {
                logger.warn("Text entry validation failed. Expected: '{}', Actual: '{}'", value, actualValue);
            }

            if (pressEnter) {
                page.press(locator, "Enter");
            }
        } catch (Exception e) {
            logger.error("Failed to fill text box: {}", locator, e);
            throw e;
        }
    }
}
