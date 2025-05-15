package org.cardinality.pages;

import java.util.List;

import org.cardinality.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RequestPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(RequestPage.class);

    public RequestPage(Page page) {
        super(page);
    }

    // String locators
    private static final String RIBBON_PERSONS = "//button[text() = ' Persons ']";
    private static final String SAMPLE="";
    private static final String AddRequestBtn="//button[contains(., 'Add Request')]";
    private static final String Export_button="//div[@id='dx-a660ba87-c1f6-a4d6-dcce-74d081d46460']//div[@class='dx-button-content']";
    private static final String Column_chooser="//i[@class='dx-icon dx-icon-column-chooser']";
    private static final String Calendar_icon_in_table="(//div[@class='dx-dropdowneditor-icon'])[1]";
    private static final String NoOfItemsDropDown="(//div[@class='dx-dropdowneditor-icon'])[2]";
    private static final String expandSearchButton="(//div[@class='dx-item dx-menu-item dx-menu-item-has-icon dx-menu-item-has-submenu dx-menu-item-expanded'])[1]";
    private static final String DoesNotEqual_option="(//span[normalize-space()='Does not equal'])[1]";
    private static final String serviceCategoryFilter="(//span[@aria-label=\"Show filter options for column 'Service Category'\"])[1]";
    private static final String providerNameFilter="(//span[@aria-label=\"Show filter options for column 'Provider Name'\"])[1]";
    private static final String dateAddedFilter="(//span[@aria-label=\"Show filter options for column 'Date Added'\"])[1]";
    private static final String prevPageNavi="//div[@aria-label='Previous Page']";
    private static final String nextPageNavi="//div[@aria-label='Next Page']";
    private static final String backBtn="//button[@name='data[back]']";
    private static final String requestedGoodsDropDwn="(//div[@class='form-control ui fluid selection dropdown'])[1]";
    private static final String descriptionIp="(//textarea[@id='e6mhnto-description'])[1]";
    private static final String requestedAmtIp="//input[@id='eywi34k-requested_amount']";
    private static final String textEditorAreas="";
    private static final String microphoneBtn="";
    private static final String providerNameIp="//input[@id='eqjkhb7-provider_name']";
    private static final String providerAddrIp="//input[@id='et2ziz-provider_address']";
    private static final String provider_contact_person_name="//input[@id='epky1fbg-provider_contact_person_name']";
    private static final String provider_email="//input[@id='euzkxis-provider_email']";
    private static final String provider_phone="//input[@id='eu5bk6-provider_phone']";
    private static final String save_button="//button[contains(text(), 'Save')]";

    public String getribbonBSITFInformationLocator() {
        return RIBBON_PERSONS;
    }

    public void clickAddPersons() {
        logger.info("Waiting for the ADD_PERSON element.");
        waitForElement(SAMPLE, "LONG_WAIT");
        logger.info("Clicking the ADD_PERSON element.");
        page.click(SAMPLE);
        logger.info("ADD_PERSON element clicked.");
    }

    public void fillPersonDetails() {
        try {
            logger.info("Waiting for the INPUT_LAST_NAME element.");
            waitForElement(SAMPLE, "LONG_WAIT");
            logger.info("Entering last name.");
            page.fill(SAMPLE, faker.name().lastName());
            logger.info("Last name entered.");

            logger.info("Waiting for the INPUT_MIDDLE_NAME element.");
            waitForElement(SAMPLE, "LONG_WAIT");
            logger.info("Entering middle name.");
            page.fill(SAMPLE, faker.name().suffix());
            logger.info("Middle name entered.");

            logger.info("Waiting for the INPUT_FIRST_NAME element.");
            waitForElement(SAMPLE, "LONG_WAIT");
            logger.info("Entering first name.");
            page.fill(SAMPLE, faker.name().firstName());
            logger.info("First name entered.");
        } catch (Exception e) {
            logger.error("Failed to fill in person details.", e);
        }
    }
}