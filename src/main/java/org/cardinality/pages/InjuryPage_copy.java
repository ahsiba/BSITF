// package org.cardinality.pages;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.List;

// import org.cardinality.base.BasePage;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.testng.Assert;

// import com.microsoft.playwright.ElementHandle;
// import com.microsoft.playwright.Locator;
// import com.microsoft.playwright.Page;
// import com.microsoft.playwright.options.WaitForSelectorState;
// import com.microsoft.playwright.PlaywrightException;


// public class InjuryPage extends BasePage {
//     private static final Logger logger = LoggerFactory.getLogger(InjuryPage.class);

//     public InjuryPage(Page page) {
//         super(page);
//     }

//     // String locators
//     private static final String INPUT_SUFFIX = "//input[@placeholder='Enter Suffix']";
//     private static final String SAMPLE="";
//     private static final String checkbox="(//label[contains(@class, 'form-check-label')]/input[contains(@class, 'form-check-input')])[<index>]";
//     private static final String dateOfInjury="//div[contains(@class, 'input-group')]/input[contains(@class, 'form-control')][2]";
//     private static final String calendarIcon="//div[contains(@class, 'input-group-append')]/span[contains(@class, 'input-group-text')]";
//     private static final String CauseOfInjuryDropdown = "(//div[contains(@class,'form-control ui fluid selection dropdown')])[1]";
//     private static final String CauseOfInjuryDropdownSearch = "(//input[@aria-autocomplete='list'])[1]";
//     private static final String CauseOfInjuryTextarea = "(//div[contains(@class, 'ql-editor')])[1]";

    

//     // Method to update BSITF information basic details
//     public int searchAndNavigateToBSITFInfo(String keyword) {
//         Locator locator = page.locator(SAMPLE);
//         logger.info("Clicking and filling the search tab with keyword: " + keyword);
//         page.click(SAMPLE);
//         page.fill(SAMPLE, keyword);
//         int count = locator.count();
//         locator.nth(0).click();
//         logger.info("Waiting for the BSITF Information ribbon to be visible.");
//         waitForElement(SAMPLE, "LONG_WAIT");
//         return count;
//     }

//     // Method to update BSITF information language
//     public int updateBSITFInformationLanguage(String value) {
//         logger.info("Locating sidebar list.");
//         Locator locator = page.locator(SAMPLE);
//         page.click(SAMPLE);
//         page.fill(SAMPLE, value);
//         int count = locator.count();
//         logger.info("Number of items found: " + count);
//         locator.nth(0).click();
//         logger.info("Waiting for the BSITF information ribbon to be visible.");
//         waitForElement(SAMPLE, "LONG_WAIT");
//         return count;
//     }

//     public void addLanguageAndVerifyLanguageIsAdded() {
//         logger.info("Checking if the language tab is expanded.");
//         // Check if the language tab is expanded or not
//         expandLanguageTabIfNeeded();
//         logger.info("Deleting existing languages.");
//         deleteExistingLanguages();
//         page.click(SAMPLE);
//         logger.info("Clicking to select language.");
//         page.click(SAMPLE);
//         page.click(SAMPLE);
//         submitAndWaitForSuccess();
//     }

//     private void expandLanguageTabIfNeeded() {
//         boolean isAriaExpandedTrue = page.getAttribute(SAMPLE, "aria-expanded").equals("true");
//         if (!isAriaExpandedTrue) {
//             logger.info("Language tab is not expanded. Hovering and clicking on the language tab.");
//             page.hover(SAMPLE);
//             page.click(SAMPLE);
//         }
//     }

//     public void deleteExistingLanguages() {
//         Locator languageElements = page.locator(SAMPLE);
//         // Get the count of existing language elements
//         int count = languageElements.count();
//         logger.info("Number of existing language elements: " + count);
//         // Iterate through each language element and delete it
//         // Delete elements from the end to avoid shifting indexes
//         for (int i = count - 1; i >= 0; i--) {
//             logger.info("Deleting language element at index: " + i);
//             languageElements.nth(i).click();
//         }
//     }


//     public void submitAndWaitForSuccess() {
//         page.hover(SAMPLE);
//         page.click(SAMPLE);
//         logger.info("Waiting for the success message to be visible.");
//         waitForElement(SAMPLE, "LONG_WAIT");
//     }    

//     public boolean checkErrorMessageForExceedingLimit() {
//         logger.info("Checking error message for exceeding character limit.");

//         // Enter more than 400 characters
//         String invalidNotes = new String(new char[401]).replace('\0', 'a');
//         page.fill(SAMPLE, invalidNotes);

//         // Verify that error message is displayed
//         boolean isErrorVisible = page.isVisible(SAMPLE);
//         logger.info("Error message validation passed for exceeding 400 characters.");
//         return isErrorVisible;
//     } 
    
//     public boolean verifyAndCheckCheckbox(Page page, String checkboxLocator, String labelLocator,
//             String checkboxName) {
//         logger.info("Verifying if " + checkboxName + " checkbox is already checked.");

//         // Check if the checkbox is already checked
//         boolean isChecked = page.isChecked(checkboxLocator);

//         if (!isChecked) {
//             logger.info(checkboxName + " checkbox is not checked. Checking now.");
//             page.check(checkboxLocator);
//         }

//         // Verify that the label is present
//         boolean isLabelVisible = page.isVisible(labelLocator);
//         if (isLabelVisible) {
//             logger.info(checkboxName + " label is present.");
//         } else {
//             logger.error(checkboxName + " label is not present.");
//         }

//         return isChecked && isLabelVisible;
//     }
    
//     public boolean selectDate(Page page, String dateLocator) {
//         logger.info("Selecting date.");

//         // Click on the date input to open the calendar
//         page.click(dateLocator);
       
//         boolean isDateSelected = page.isVisible(SAMPLE);

//         // Select the date
//         page.click(SAMPLE);
//         // Verify that the date is selected
//         if (isDateSelected) {
//             logger.info("Date is successfully selected.");
//         } else {
//             logger.error("Failed to select the date.");
//         }

//         return isDateSelected;
//     }

//     private void selectLanguageFromList(int index) {
//         logger.info("Selecting language from the list.");
//         // Get the list of languages
//         List<ElementHandle> languages = page.querySelectorAll(SAMPLE);

//         // Ensure the index is within bounds
//         if (index < languages.size()) {
//             languages.get(index).click();
//             logger.info("Selected language at index: " + index);
//         } else {
//             logger.error("Index out of bounds. Total languages available: " + languages.size());
//         }
//     }


// }