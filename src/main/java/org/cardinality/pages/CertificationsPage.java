package org.cardinality.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificationsPage {

    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(CertificationsPage.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final String Signature_canvas_element="//canvas[1]";
    private static final String Submit_application_Btn="//button[@class='btn btn-success text-nowrap ng-star-inserted']";

    public CertificationsPage(Page page) {

        this.page = page;

    }

    public void enableSubmitCheckBox(){
        page.locator("//div[contains(@class,'form-check checkbox')]//input").click();
    }
    public void clickMenu(String menuName) {
        int attempts = 0;
        boolean success = false;
        Exception lastException = null;

        while (!success && attempts < MAX_RETRY_ATTEMPTS) {
            try {
                Locator menu = page.locator("//span[text()='" + menuName + "']").last();
                menu.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                menu.scrollIntoViewIfNeeded();
                menu.click();
                Locator loadingImage = page.locator("//img[@class='logoCenterLoading']");
                loadingImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                loadingImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
                success = true;
            } catch (Exception e) {
                lastException = e;
                attempts++;
                logger.warn("Attempt {} to click menu '{}' failed. Retrying...", attempts, menuName);
            }
        }

        if (!success) {
            logger.error("Failed to click menu '{}' after {} attempts", menuName, MAX_RETRY_ATTEMPTS);
            throw new RuntimeException("Failed to click menu: " + menuName, lastException);
        }
    }

}
