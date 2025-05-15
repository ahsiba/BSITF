package org.cardinality.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.cardinality.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LivingSurveyPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LivingSurveyPage.class);
    private static final String Sidebar_Icons="(//div[@class='col text-center'])[<idx>]";
    private static final String question1radioBtns="(//input[starts-with(@id, 'ev5wand-e1bni2--')])[idx]";
    private static final String quest2radioBtn="(//input[starts-with(@id, 'ev5wand-e8ezksp--')])[idx]";
    private static final String quest3radioBtn="(//input[starts-with(@id, 'ev5wand-ense3eu--')])[idx]";

    public LivingSurveyPage(Page page) {
        super(page);
    }

    public void deletePhoto(String columnNumber) {
        logger.info("Attempting to delete photo in column: {}", columnNumber);
        Locator delete = page.locator("(//i[@class='fa fa-trash-o ng-star-inserted'])[" + columnNumber + "]");
        delete.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        delete.click();
        logger.info("Clicked delete button for photo in column: {}", columnNumber);
    }

    public void photoDelete(String columnNumber) {
        logger.info("Starting photo deletion process for column: {}", columnNumber);
        deletePhoto(columnNumber);
        confirmPhotoDelete();
        logger.info("Photo deletion process completed for column: {}", columnNumber);
    }

    public void deletePrimaryPhoto() {
        logger.info("Attempting to delete primary photo");
        Locator primary = page.locator("//a[i[contains(@class, 'fa-trash-o')]]    [following-sibling::a[i[contains(@class, 'fa-star-o') " +
                "and contains(@class, 'active')]]] //i[contains(@class, 'fa-trash-o')]");
        primary.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        primary.click();
        logger.info("Clicked delete button for primary photo");
    }

    public void primaryPhotoDelete() {
        logger.info("Starting primary photo deletion process");
        logger.info("Attempting to delete the primary photo");
        deletePrimaryPhoto();
        logger.info("Confirming primary photo deletion");
        boolean isDeleted = confirmPhotoDelete();
        if (isDeleted) {
            logger.info("Primary photo was successfully deleted");
        } else {
            logger.error("Failed to delete the primary photo");
        }
        logger.info("Primary photo deletion process completed");
    }

    public boolean confirmPhotoDelete(int timeoutSeconds) {
        logger.info("Confirming photo deletion");
        Locator confirmButton = page.locator("//button[contains(@class,'btncommon delete') and text()=' Yes ']");
        confirmButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        confirmButton.click();
        logger.info("Clicked 'Yes' to confirm photo deletion");

        Locator successMessage = page.locator("//div[text()=' Deleted Successfully. ']");
        try {
            successMessage.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(timeoutSeconds * 1000));
            boolean isSuccessMessageVisible = successMessage.isVisible();
            logger.info("Success message visibility: {}", isSuccessMessageVisible);
            return isSuccessMessageVisible;
        } catch (com.microsoft.playwright.TimeoutError e) {
            logger.error("Timeout waiting for success message: {}", e.getMessage());
            return false;
        }
    }

    // Overloaded method with default timeout
    public boolean confirmPhotoDelete() {
        return confirmPhotoDelete(30); // Default 30 seconds timeout
    }
    public void cancelDelete() {
        logger.info("Cancelling photo deletion");
        page.locator("//div[contains(@class, 'text-right')]/button[contains(@class, " +
                "'btncancel') and text()='No']").click();
        logger.info("Clicked 'No' to cancel photo deletion");
    }
}




