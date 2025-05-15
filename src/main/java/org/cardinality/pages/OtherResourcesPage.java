package org.cardinality.pages;

import org.cardinality.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OtherResourcesPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(OtherResourcesPage.class);

    public OtherResourcesPage(Page page) {
        super(page);
    }

    // String locators
    private static final String RIBBON_ALERTS = "//button[text() = ' Alerts ']";
    private static final String SAMPLE="";
    private static final String successAlert="//div[contains(@class, 'alert') and contains(@class, 'alert-success')]";
    private static final String checkBoxes="(//label[contains(@class, 'form-check-label')]/input[contains(@class, 'form-check-input')])[<index>]";
    private static final String otherWaviersIp="//input[@id='elkx1cq-other_waivers']";
    private static final String textInput="(//div[contains(@class, 'ql-editor')])[<index>]";
    private static final String BackBtn="(//button[contains(text(), 'Back' )])[2]";
    private static final String BackToDashboardBtn="(//button[contains(text(), 'Back' )])[1]";
    private static final String microphoneBtn="(//button[contains(@class, 'narrative-speech-btn')])[<index>]";
    private static final String textHeadingLevel ="(//span[contains(@class, 'ql-header') and contains(@class, 'ql-picker')]/span[contains(@class, 'ql-picker-label')])<index>";
    private static final String fontType="(//span[contains(@class, 'ql-font') and contains(@class, 'ql-picker')]/span[contains(@class, 'ql-picker-label')])[<index>]";
    private static final String fontBold="(//button[contains(@class, 'ql-bold')])[<indx>]";
    private static final String fontItalic="(//button[contains(@class, 'ql-italic')])[<idx>]";
    private static final String fontUnderline="(//button[contains(@class, 'ql-underline')])[<idx>]";
    private static final String textList="(//button[contains(@class, 'ql-list')])[<idx>]";
    private static final String textColor="(//span[contains(@class, 'ql-color') and contains(@class, 'ql-picker')]/span[contains(@class, 'ql-picker-label')])[1]";
    private static final String Total_4_indexes="(//span[contains(@class, 'ql-color') and contains(@class, 'ql-picker')]/span[contains(@class, 'ql-picker-label')])[2]";
    private static final String textAlign="//span[contains(@class, 'ql-align') and contains(@class, 'ql-picker')]/span[contains(@class, 'ql-picker-label')]";
    


    // Method to update BSITF information basic details
    public int searchAndNavigateToBSITFNavigator(String keyword) {
        Locator locator = page.locator(SAMPLE);
        logger.info("Clicking and filling the search tab with keyword: " + keyword);
        page.click(SAMPLE);
        page.fill(SAMPLE, keyword);
        int count = locator.count();
        locator.nth(0).click();
        logger.info("Waiting for the " + keyword + " ribbon to be visible.");
        waitForElement(RIBBON_ALERTS, "LONG_WAIT");
        return count;
    }

    public String getRibbonBSITFInformationLocator() {
        return RIBBON_ALERTS;
    }

    public void addAlerts() {
        logger.info("Waiting for the ADD_ALERTS element.");
        waitForElement(SAMPLE, "LONG_WAIT");
        logger.info("Clicking the ADD_ALERTS element.");
        page.click(SAMPLE);
        logger.info("ADD_ALERTS element clicked.");
    }

    public void selectSeverity() {
        waitForElement(SAMPLE, "LONG_WAIT");
        page.click(SAMPLE);
        // Wait for the choices list to be visible
        waitForElement(SAMPLE, "SHORT_WAIT");

        // Select the first option from the choices list
        page.click(SAMPLE + "[2]");
    }

    public void selectDescription() {
        waitForElement(SAMPLE, "LONG_WAIT");
        page.click(SAMPLE);
        // Wait for the choices list to be visible
        waitForElement(SAMPLE, "SHORT_WAIT");

        // Select the first option from the choices list
        page.click(SAMPLE + "[2]");
    }

    public void setStartDate() {
        logger.info("Setting the start date.");
        waitForElement(SAMPLE, "SHORT_WAIT");
        page.click(SAMPLE);
        waitForElement(SAMPLE, "SHORT_WAIT");
        page.click(SAMPLE);
        logger.info("Start date set to today.");
    }

    public void setExpireDate() {
        logger.info("Setting the expire date.");
        waitForElement(SAMPLE, "SHORT_WAIT");
        page.click(SAMPLE);
        waitForElement(SAMPLE, "SHORT_WAIT");
        page.click(SAMPLE);
        logger.info("Expire date set to today.");
    }

    public void clickSaveButton() {
        logger.info("Waiting for the SAVE button.");
        waitForElement(SAMPLE, "SHORT_WAIT");
        logger.info("Clicking the SAVE button.");
        page.click(SAMPLE);
        logger.info("SAVE button clicked.");
        waitForElement(SAMPLE, "LONG_WAIT");
    }

    // Method to check if the success message is present
    public boolean isSubmitSuccessMessagePresent() {
        return page.isVisible(SAMPLE);
    }


    public void filterByStatus(String status) {
        logger.info("Clicking on the status filter cell.");
        waitForElement(SAMPLE, "LONG_WAIT");
        page.click(SAMPLE);

        logger.info("Clearing the status filter cell.");
        page.fill(SAMPLE, status);

        logger.info("Entering in the status filter cell.");
        page.fill(SAMPLE, status);

        logger.info("Status filter set");
    }   

    public void clickEditAlert(int index) {
        logger.info("Waiting for the EDIT_ALERT element.");
        String strLocator = String.format(SAMPLE, index);
        waitForElement(strLocator, "LONG_WAIT");
        logger.info("Clicking the EDIT_ALERT element.");
        page.click(strLocator);
        waitForElement(strLocator, "LONG_WAIT");
        logger.info("EDIT_ALERT element clicked.");
    }
    public boolean isExpiredDateIsNotDisplayed() {
        logger.info("Checking if the 'Expired' element is displayed.");
        waitForElement(SAMPLE, "LONG_WAIT");
        // Use nth-match to select the first occurrence of the 'Expired' element
        boolean isDisplayed = page.locator(":nth-match(:text('Expired'), 1)").isVisible();
        logger.info("'Expired' element displayed: " + isDisplayed);
        return isDisplayed;
    }

}