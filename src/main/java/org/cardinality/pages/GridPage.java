package org.cardinality.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GridPage {
    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(GridPage.class);
    private static final String Start_Application_Button="//button[contains(@class, 'btn') and contains(@class, 'btn-primary') and contains(@class, 'ng-star-inserted')]";

    public GridPage(Page page) {
        this.page = page;
    }

    // Click Add button dynamically
    public void clickAdd(String buttonName) {
        logger.debug("Starting to click 'Add' button with name: {}", buttonName);

        Locator button = page.locator("//span[text()='" + buttonName + "']");
        logger.debug("Waiting for 'Add' button to be visible...");
        button.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        logger.debug("'Add' button is visible. Clicking it...");
        button.click();
        waitForLoaderToDisappear();
        logger.debug("'Add' button click completed.");
    }

    public void clickEdit(String columnNumber) {
        logger.debug("Starting to click 'Edit' button for column: {}", columnNumber);

        Locator edit = page.locator("(//em[@class='fa fa-pencil'])[" + columnNumber + "]");
        logger.debug("Waiting for 'Edit' button to be visible...");
        edit.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        logger.debug("'Edit' button is visible. Clicking it...");
        edit.click();
        waitForLoaderToDisappear();
        logger.debug("'Edit' button click completed.");
    }

    public void clickView(String columnNumber) {
        logger.debug("Starting to click 'View' button for column: {}", columnNumber);

        Locator view = page.locator("(//em[@class='fa fa-eye'])[" + columnNumber + "]");
        logger.debug("Waiting for 'View' button to be visible...");
        view.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        logger.debug("'View' button is visible. Clicking it...");
        view.click();
        waitForLoaderToDisappear();
        logger.debug("'View' button click completed.");
    }

    public void waitForLoaderToDisappear() {
        Locator loadingImage = page.locator("//img[@class='logoCenterLoading']");

        try {
            logger.debug("Waiting for loading image to be visible...");
            loadingImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            logger.debug("Loading image is visible. Waiting for it to disappear...");
            loadingImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));

            logger.debug("Loading image has disappeared.");
        } catch (Exception e) {
            logger.error("Error while waiting for loading image to disappear: {}", e.getMessage(), e);
            throw e;
        }
    }

    // Click Delete button based on column number
    public void clickDeleteInGrid(String columnNumber) {
        logger.debug("Starting deleteAfterThreshold for column: {}", columnNumber);

        // Locate and click the delete button
        Locator delete = page.locator("(//em[@class='fa fa-trash'])[" + columnNumber + "]");
        logger.debug("Waiting for delete button to be visible...");
        delete.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        logger.debug("Delete button is visible. Clicking it...");
        delete.click();
        waitForLoaderToDisappear();
        logger.debug("'Delete' button click completed.");
    }

    public void clickingDeleteAfterThreshold(String columnNumber, String text) {

        clickDeleteInGrid(columnNumber);

        // Fill in the reason for deletion
        Locator reason = page.locator("#reasonfordelete");
        logger.debug("Waiting for reason input field to be visible...");
        reason.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        logger.debug("Reason input field is visible. Filling in the reason: {}", text);
        reason.fill(text);

        logger.debug("deleteAfterThreshold completed for column: {}", columnNumber);
    }


    public boolean clickYesOnDeleteAfterThresholdTime() {
        logger.debug("Starting confirmDeleteAfterThresholdTime...");

        Locator confirmButton = page.locator("//button[contains(@class,'btn-primary delete')]");
        logger.debug("Waiting for confirm button to be visible...");
        confirmButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        logger.debug("Confirm button is visible. Clicking it...");
        confirmButton.click();

        Locator successMessage = page.locator("//div[text()=' Delete Requested Successfully ']");

        try {
            logger.debug("Waiting for success message: 'Delete Requested Successfully'...");
            successMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            logger.info("Success message is visible: 'Delete Requested Successfully'.");
            return true; // Return true if the success message is visible
        } catch (Exception e) {
            logger.error("Success message not found. Deletion may have failed.");
            return false; // Return false if the success message is not visible
        }
    }

    public boolean clickYesForDelete() {
        logger.debug("Starting clickYesForDelete...");

        Locator confirmButton = page.locator("//button[contains(@class,'btn-primary delete')]");
        logger.debug("Waiting for confirm button to be visible...");

        try {
            confirmButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            logger.debug("Confirm button is visible. Clicking it...");
            confirmButton.click();
        } catch (Exception e) {
            logger.error("Failed to click the confirm button: {}", e.getMessage(), e);
            return false;
        }
        Locator successMessage = page.locator("//div[text()=' Deleted Successfully ']");
        try {
            logger.debug("Waiting for success message: 'Deleted Successfully'...");
            successMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            logger.info("Success message is visible: 'Deleted Successfully'.");
            return true; // Return true if success message is found
        } catch (Exception e) {
            logger.error("Success message not found. Deletion may have failed: {}", e.getMessage(), e);
            return false;
        }
    }
    public void cancelDelete() {
        try {
            logger.debug("Attempting to click 'No' button to cancel the deletion...");

            Locator cancelButton = page.locator("//div[contains(@class, 'text-right')]/button[contains(@class, 'btncancel') and text()='No']");
            cancelButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000)); // Optional wait
            cancelButton.click();

            logger.debug("'No' button clicked. Deletion has been cancelled.");
        } catch (Exception e) {
            logger.error("Error while attempting to cancel deletion: {}", e.getMessage(), e);
            throw e;
        }
    }


    public void search(String magnifier, String searchTerm, String textBox, String value) {
        logger.debug("Starting search operation...");
        logger.debug("Magnifier: {}, Search Term: {}, Text Box: {}, Value: {}", magnifier, searchTerm, textBox, value);

        try {
            // Step 1: Click the magnifier dropdown
            logger.debug("Clicking magnifier dropdown...");
            page.locator("(//span[@class='dx-menu-item-popout-container'])[" + magnifier + "]").click();
            logger.debug("Magnifier dropdown clicked.");

            // Step 2: Select the search term
            logger.debug("Selecting search term: {}", searchTerm);
            page.locator("//span[text()='" + searchTerm + "']").click();
            logger.debug("Search term selected.");

            // Step 3: Locate and fill the input field
            logger.debug("Locating input field for text box: {}", textBox);
            Locator input = page.locator("(//input[@class='dx-texteditor-input'])[" + textBox + "]");
            logger.debug("Waiting for input field to be visible...");
            input.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            logger.debug("Input field is visible. Filling in the value: {}", value);
            input.fill(value);
            logger.debug("Value filled in the input field.");

            logger.info("Search operation completed successfully.");
        } catch (TimeoutError e) {
            logger.error("Timeout while performing search operation: {}", e.getMessage(), e);
            throw e; // Rethrow to fail the test
        } catch (Exception e) {
            logger.error("Unexpected error during search operation: {}", e.getMessage(), e);
            throw e; // Rethrow to fail the test
        }
    }
    public List<String> getSearchResults(String columnNumber) {
        logger.debug("Starting to retrieve search results for column number: {}", columnNumber);

        try {
            // Optional wait (use explicit waits if possible)
            page.waitForTimeout(2000);
            logger.debug("Waited for 2 seconds to allow search results to load.");

            // Locate the result cells
            Locator results = page.locator("(//tbody)[2]//td[@aria-colindex='" + columnNumber + "']");
            logger.debug("Locator for results defined. Waiting for the first result to be visible...");

            results.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            logger.debug("First result is visible. Fetching all inner texts...");

            List<String> resultTexts = results.allInnerTexts();
            logger.info("Successfully retrieved {} search results.", resultTexts.size());

            return resultTexts;

        } catch (TimeoutError e) {
            logger.error("Timeout while retrieving search results for column {}: {}", columnNumber, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while retrieving search results for column {}: {}", columnNumber, e.getMessage(), e);
            throw e;
        }
    }
    //Delete after threshold time
    public boolean deleteRecordAfterThresholdTime(String columnNumber, String text) {
        logger.debug("Starting deleteRecordAfterThresholdTime for column: {}", columnNumber);
        clickingDeleteAfterThreshold(columnNumber, text);
        boolean isDeleted = clickYesOnDeleteAfterThresholdTime() ;
        if (isDeleted) {
            logger.debug("Record deletion was successful for column: {}", columnNumber);
        } else {
            logger.error("Record deletion failed for column: {}", columnNumber);
        }
        return isDeleted;
    }

    public boolean deleteRecordInGrid(String columnNumber) {
        try {
            logger.debug("Attempting to delete record at column: {}", columnNumber);

            clickDeleteInGrid(columnNumber);
            logger.debug("Clicked delete button for column: {}", columnNumber);

            boolean isDeleted = clickYesForDelete();
            logger.debug("Confirmed deletion by clicking 'Yes'. Result: {}", isDeleted);


            return isDeleted;
        } catch (Exception e) {
            logger.error("Error while deleting record at column {}: {}", columnNumber, e.getMessage(), e);
            throw e;
        }
    }


}