package org.cardinality.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.cardinality.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.PlaywrightException;


public class InjuryPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(InjuryPage.class);

    public InjuryPage(Page page) {
        super(page);
    }

    // String locators
    private static final String INPUT_SUFFIX = "//input[@placeholder='Enter Suffix']";
    private static final String SAMPLE="";
    private static final String checkbox="//*[@class='form-check-label']";
    private static final String radioBtn="//*[@class='form-check-label label-position-right']";
    private static final String dateOfInjury="//div[contains(@class, 'input-group')]/input[contains(@class, 'form-control')][2]";
    private static final String calendarIcon="//div[contains(@class, 'input-group-append')]/span[contains(@class, 'input-group-text')]";
    private static final String CauseOfInjuryDropdown = "(//div[contains(@class,'form-control ui fluid selection dropdown')])[1]";
    private static final String CauseOfInjuryDropdownSearch = "(//input[@aria-autocomplete='list'])[1]";
    private static final String CauseOfInjuryTextarea = "(//div[contains(@class, 'ql-editor')])[1]";

    

    public void verifyNatureInjurySection() {
    try {
        logger.info("Verifying Nature of Injury section");
        String resolvedCheckboxXpath = checkbox.replace("<index>", "0");

        Locator tbiCheckbox = page.locator(resolvedCheckboxXpath);
        waitForElement(resolvedCheckboxXpath, "DEFAULT_WAIT");
        if (!tbiCheckbox.isChecked()) {
            tbiCheckbox.check();
            logger.info("Checked Traumatic Brain Injury");
        }

        // Optionally select 'Spinal Cord Injury (SCI)' (optional step - can be skipped)
        String sciCheckboxXpath = checkbox.replace("<index>", "1");
        Locator sciCheckbox = page.locator(sciCheckboxXpath);
        waitForElement(sciCheckboxXpath, "DEFAULT_WAIT");

        if (!sciCheckbox.isChecked()) {
            sciCheckbox.check();  // optional - remove this if you only want to test TBI
            logger.info("Checked Spinal Cord Injury");
        }

        String radioBtnXpath = checkbox.replace("<index>", "2");
        Locator radioBtn = page.locator(radioBtnXpath);
        waitForElement(radioBtnXpath, "DEFAULT_WAIT");
        if (!radioBtn.isChecked()) {
            radioBtn.check();
            logger.info("paraplegcia radio button checked");
        }
       

        // Fill Date of Injury
        Locator injuryDate = page.locator(dateOfInjury);
        waitForElement(dateOfInjury, "DEFAULT_WAIT");
        injuryDate.fill("05-01-2025");
        logger.info("Filled Date of Injury");

    } catch (PlaywrightException e) {
        logger.error("Failed to verify or fill Injury section: " + e.getMessage());
        throw e;
    }
}

    public void verifyCauseInjurySection() {
        try {
            logger.info("Verifying Cause of Injury section");

            // Select value from dropdown
            Locator dropdown = page.locator(CauseOfInjuryDropdown);
            waitForElement("xpath=" + CauseOfInjuryDropdown, "DEFAULT_WAIT");
            dropdown.click();

            Locator dropdownSearch = page.locator(CauseOfInjuryDropdownSearch);
            dropdownSearch.fill("Assault");
            dropdownSearch.press("Enter");
            logger.info("Selected 'Assault' from Cause of Injury dropdown");

            // Fill in text area
            Locator injuryTextarea = page.locator(CauseOfInjuryTextarea);
            waitForElement("xpath=" + CauseOfInjuryTextarea, "DEFAULT_WAIT");
            injuryTextarea.fill("This is for the testing purpose.");
            logger.info("Filled Cause of Injury details");

        } catch (Exception e) {
            logger.error("Failed to verify/fill Cause of Injury section: " + e.getMessage());
            throw e;
        }
    }


}