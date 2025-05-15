package org.cardinality.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import org.cardinality.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicantInfoPage extends BasePage{
    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(ApplicantInfoPage.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;

    private static final String StartApplicationButton = "//button[@class='btn btn-primary ng-star-inserted' and normalize-space(.)='Start Application']";
    private static final String dropDownBtn = "(//div[contains(@class,'form-control ui fluid selection dropdown')])[index]";

    // locators for demographics section
    private static final String Demographics_FirstNameIp = "(//div[@ref='element'])[4]";
    private static final String Demographics_LastNameIp = "(//div[@ref='element'])[5]";
    private static final String Demographics_EmailAddressIp = "(//div[@ref='element'])[6]";
    private static final String Demographics_DaytimePhoneIp = "(//div[@ref='element'])[7]";
    private static final String Demographics_AlternatePhoneIp = "//input[@id='eqfn1f9-applicant_info.alt_phone']";
    private static final String Demographics_OccupationIp = "//input[@id='egp03c-applicant_info.occupation']"; 
    private static final String Demographics_EmployerIp = "//input[@id='e1x2j8-applicant_info.employer']";
    private static final String Demographics_SSN_Ip = "//lib-ssn-input[@id='ed7op1-applicant_info.ssn']";
    private static final String Demographics_GenderIp = "//div[@id='e3zpb7n-e9qkgpt--0']";
    private static final String Demographics_DateofBirthIp = "//div[@id='e3zpb7n-e9qkgpt--1']";
    private static final String Demographics_StreetAddressIp = "//div[@id='e38qv16']/*[2]";
    private static final String Demographics_CityIp = "//div[@id='e1axdb9']/*[2]";
    private static final String Demographics_CountyIp = "//div[@id='e5areuj']/*[2]";
    private static final String Demographics_StateIp = "//div[@id='eb0p6gm']/*[2]";
    private static final String Demographics_ZipCodeIp = "//div[@id='ecr8mak']/*[2]";
    private static final String Demographics_MailingAddressCheckbox = "//label[@class='form-check-label']/*[1]";
    private static final String Demographics_AddAnotherBtn = "//button[@class='btn btn-primary ng-star-inserted' and normalize-space(.)='Add Another']";
    private static final String Demographics_MailingAddressIp = "//input[@placeholder='Enter Mailing Address']";
    private static final String Demographics_MailingCityIp = "//input[@id='e0nf53i-applicant_address.city']";
    private static final String Demographics_MailingCountyIp = "//input[@id='egqzzs-applicant_address.county']";
    private static final String Demographics_MailingStateIp = "//div[@id='etrmqs']/*[2]";
    private static final String Demographics_MailingZipCodeIp = "//input[@id='ehb6ye-applicant_address.zip_code']";

    private static final String ApplicationID ="//div[contains(@class, 'application-number')]/div[1]/p[2]";
    
    // locators for race
    private static final String raceLabelXpath = "//label[@for='e3695ro-applicant_info.race_id']";

    // locators for residency section
    private static final String ResidencyErrorMessage="//div[contains(text(),'You must be a resident of Georgia to submit an app')]";
    private static final String ResidentYesRadioBtn="(//input[starts-with(@id, 'e3zpb7n-e4ysyl3--')])[<index>]";
    private static final String ResidenceCountyIp="//input[@id='en9oufh-applicant_residency.county_of_residence']";
    private static final String EmploymentYesRadioBtn="(//input[starts-with(@id, 'e3zpb7n-ei0xo1h--')])[<index>]";
    private static final String PermHomeYesRadioBtn="(//input[starts-with(@id, 'e3zpb7n-exsdm1j--')])[<index>]";
    private static final String USCitizenYesRadioBtn="(//input[starts-with(@id, 'e3zpb7n-e9qkgpt--')])[<index>]";
    private static final String USCitizenErrorMessage="//div[contains(text(),'You must be a citizen of United States.')]";
    
    private static final String ApplCompletedByDropDwnSelect="//div[@id='exnum8t'][contains(., 'Application Completed By')]//div[contains(@class, 'form-control') and contains(@class, 'dropdown')]";
    private static final String ApplCompletedByDropDwnListn="//div[@id='exnum8t'][contains(., 'Application Completed By')]//div[contains(@class, 'choices__list')]/div[contains(@class, 'choices__item')][1]";
    private static final String ApplCompletedByDropDwnSearchBox="//div[@id='exnum8t'][contains(., 'Application Completed By')]//input[contains(@class, 'choices__input--cloned')]";
    private static final String ApplCompletedByNameIp="//input[@id='e0qi7si-applicant_relation.name']";
    private static final String ApplCompletedByMailingAddrIp="";
    private static final String ApplCompletedByCityIp="//input[@id='epx3smq-applicant_relation.city']";
    private static final String ApplCompletedByCountryIp="//input[@id='epx3smq-applicant_relation.county']";
    private static final String ApplCompletedByStateIp="//input[@id='ez96kr4-applicant_relation.state']";
    private static final String ApplCompletedByZipCodeIp="//input[@id='epemj98-applicant_relation.zip_code']";
    private static final String ApplCompletedByDaytimePhoneIp="//input[@id='emmqhx-applicant_relation.alt_phone']";
    private static final String ApplCompletedByEmailAddrIp="//input[@id='evuqhul-applicant_relation.email_address']";
    private static final String ApplCompletedByRelationIp="//input[@id='esm4g9i-applicant_relation.relation']";
    private static final String NextBtn="//button[contains(text(), 'Next')]";
    private static final String BackToDashboardBtn="//button[contains(text(), 'Back to Dashboard')]";
    private static final String ApplicationInfoBtn="(//button[contains(@class, 'stepper')])[1]";
    private static final String OtherResourcesBtn="(//button[contains(@class, 'stepper')])[2]";
    private static final String InjuryBtn="(//button[contains(@class, 'stepper')])[3]";
    private static final String RequestBtn="";
    private static final String Error="//div[@id='e-e3abzu-applicant_residency.resident_of_ga']";
    private static final String DropDownSelects="(//div[contains(@class, 'form-control') and contains(@class, 'dropdown')])[<index>]";
    private static final String DropDownSearchBar="(//input[contains(@class, 'choices__input--cloned')])[<index>]";

    public ApplicantInfoPage(Page page) {
        super(page);
        this.page = page;
    }

    public static void clickStartApplicationButton() {
        try {
            logger.info("Waiting for Start Application button to be visible");
            page.waitForLoadState(LoadState.NETWORKIDLE);  // stabilizes JS-heavy pages
            logger.info("Page loaded, waiting for Start Application button");
            page.waitForSelector("xpath=" + StartApplicationButton, 
                new Page.WaitForSelectorOptions().setTimeout(60000));

            boolean isVisible = page.isVisible("xpath=" + StartApplicationButton);
            System.out.println("Visible? " + isVisible);

            page.click("xpath=" + StartApplicationButton);  // simplified click

            logger.info("Clicked on Start Application button");
        } catch (PlaywrightException e) {
            logger.error("Failed to click on Start Application button: " + e.getMessage());
        }
    }

    public void verifyDemographics() {
    try {
        logger.info("Verifying and filling mandatory demographics fields");

        // First Name
        Locator firstName = page.locator(Demographics_FirstNameIp);
        waitForElement("xpath=" + Demographics_FirstNameIp, "DEFAULT_WAIT");
        if (firstName.inputValue().isEmpty()) {
            firstName.fill("Mukul");
        }

        // Last Name
        Locator lastName = page.locator(Demographics_LastNameIp);
        waitForElement("xpath=" + Demographics_LastNameIp, "DEFAULT_WAIT");
        if (lastName.inputValue().isEmpty()) {
            lastName.fill("Shivam");
        }

        // Email
        Locator email = page.locator(Demographics_EmailAddressIp);
        waitForElement("xpath=" + Demographics_EmailAddressIp, "DEFAULT_WAIT");
        if (email.inputValue().isEmpty()) {
            email.fill("testuser@example.com");
        }

        // Daytime Phone
        Locator phone = page.locator(Demographics_DaytimePhoneIp);
        waitForElement("xpath=" + Demographics_DaytimePhoneIp, "DEFAULT_WAIT");
        if (phone.inputValue().isEmpty()) {
            phone.fill("999-999-9999");
        }

        // Alternate Phone
        Locator altPhone = page.locator(Demographics_AlternatePhoneIp);
        waitForElement("xpath=" + Demographics_AlternatePhoneIp, "DEFAULT_WAIT");
        if (altPhone.inputValue().isEmpty()) {
            altPhone.fill("999-999-9999");
        }

        // Occupation
        Locator occupation = page.locator(Demographics_OccupationIp);
        waitForElement("xpath=" + Demographics_OccupationIp, "DEFAULT_WAIT");
        if (occupation.inputValue().isEmpty()) {
            occupation.fill("Software Engineer");
        }

        // Employer
        Locator employer = page.locator(Demographics_EmployerIp);
        waitForElement("xpath=" + Demographics_EmployerIp, "DEFAULT_WAIT");
        if (employer.inputValue().isEmpty()) {
            employer.fill("Cardinality");
        }

        // SSN (Just ensure it's present - don't fill)
        waitForElement("xpath=" + Demographics_SSN_Ip, "DEFAULT_WAIT");

        // City
        Locator city = page.locator(Demographics_CityIp);
        waitForElement("xpath=" + Demographics_CityIp, "DEFAULT_WAIT");
        if (city.inputValue().isEmpty()) {
            city.fill("Cartersville");
        }

        // County
        Locator county = page.locator(Demographics_CountyIp);
        waitForElement("xpath=" + Demographics_CountyIp, "DEFAULT_WAIT");
        if (county.inputValue().isEmpty()) {
            county.fill("Bartow");
        }

        // State
        Locator state = page.locator(Demographics_StateIp);
        waitForElement("xpath=" + Demographics_StateIp, "DEFAULT_WAIT");
        if (state.inputValue().isEmpty()) {
            state.fill("Georgia");
        }

        // ZIP Code
        Locator zip = page.locator(Demographics_ZipCodeIp);
        waitForElement("xpath=" + Demographics_ZipCodeIp, "DEFAULT_WAIT");
        if (zip.inputValue().isEmpty()) {
            zip.fill("30121");
        }

        // Mailing Address
        Locator mailingAddr = page.locator(Demographics_MailingAddressIp);
        waitForElement("xpath=" + Demographics_MailingAddressIp, "DEFAULT_WAIT");
        if (mailingAddr.inputValue().isEmpty()) {
            mailingAddr.fill("123 Main St");
        }

        // Mailing City
        Locator mailingCity = page.locator(Demographics_MailingCityIp);
        waitForElement("xpath=" + Demographics_MailingCityIp, "DEFAULT_WAIT");
        if (mailingCity.inputValue().isEmpty()) {
            mailingCity.fill("Cartersville");
        }

        // Mailing County
        Locator mailingCounty = page.locator(Demographics_MailingCountyIp);
        waitForElement("xpath=" + Demographics_MailingCountyIp, "DEFAULT_WAIT");
        if (mailingCounty.inputValue().isEmpty()) {
            mailingCounty.fill("Bartow");
        }

        // Mailing State
        Locator mailingState = page.locator(Demographics_MailingStateIp);
        waitForElement("xpath=" + Demographics_MailingStateIp, "DEFAULT_WAIT");
        if (mailingState.inputValue().isEmpty()) {
            mailingState.fill("Georgia");
        }

        // Mailing ZIP
        Locator mailingZip = page.locator(Demographics_MailingZipCodeIp);
        waitForElement("xpath=" + Demographics_MailingZipCodeIp, "DEFAULT_WAIT");
        if (mailingZip.inputValue().isEmpty()) {
            mailingZip.fill("30121");
        }

        logger.info("All mandatory demographics fields verified and filled where needed");

    } catch (PlaywrightException e) {
        logger.error("Failed to verify/fill demographics: " + e.getMessage());
        throw e;
        }
    }

    public void verifyRace() {
        // Locate the label element
        Locator raceLabel = page.locator(raceLabelXpath);

        // Use evaluate to access the ::after pseudo-element's computed style
        String afterContent = raceLabel.evaluate("label => getComputedStyle(label, '::after').content").toString();

        // Log what we found
        logger.info("Race label ::after content: " + afterContent);

        // Check if an asterisk is visually added
        if (afterContent.replace("\"", "").trim().equals("*")) {
            throw new AssertionError("Race field appears required (asterisk present via CSS), but it should be optional.");
        }

        // Check dropdown is present
        String resolvedDropDownXPath = dropDownBtn.replace("index", "2");
        Locator raceDropdown = page.locator(resolvedDropDownXPath);
        
        if (!raceDropdown.isVisible()) {
            throw new AssertionError("Race dropdown is not visible.");
        }

        logger.info("'Race' label visually does NOT contain required asterisk. Dropdown is functional.");
    }

    public void verifyHouseHoldIncome() {
        String resolvedDropDownXPath = dropDownBtn.replace("index", "3");
        Locator householdIncomeDropdown = page.locator(resolvedDropDownXPath);

        // Verify dropdown is visible and enabled
        if (!householdIncomeDropdown.isVisible()) {
            throw new AssertionError("Household Income dropdown is not visible.");
        }

        if (!householdIncomeDropdown.isEnabled()) {
            throw new AssertionError("Household Income dropdown is not enabled.");
        }

        // Click to expand
        householdIncomeDropdown.click();

        // Use relative locator to get the options list
        Locator optionsList = householdIncomeDropdown.locator(".//div[@role='listbox']");

        if (!optionsList.isVisible()) {
            throw new AssertionError("Dropdown list for 'Household Income' did not open on click.");
        }

        logger.info("'Household Income' dropdown is visible, enabled, and successfully displays options.");
    }

    public void verifyTrustFund() {
        String resolvedDropDownXPath = dropDownBtn.replace("index", "4");
        Locator trustFundDropdown = page.locator(resolvedDropDownXPath);

        // Verify dropdown is visible and enabled
        if (!trustFundDropdown.isVisible()) {
            throw new AssertionError("Trust Fund dropdown is not visible.");
        }

        if (!trustFundDropdown.isEnabled()) {
            throw new AssertionError("Trust Fund dropdown is not enabled.");
        }

        // Click to expand
        trustFundDropdown.click();

        // Use relative locator to get the options list
        Locator optionsList = trustFundDropdown.locator(".//div[@role='listbox']");

        if (!optionsList.isVisible()) {
            throw new AssertionError("Dropdown list for 'Trust Fund' did not open on click.");
        }

        logger.info("'Trust Fund' dropdown is visible, enabled, and successfully displays options.");
    }

    public void verifyResidencyRequirementsSelectOptionNO() {
        try {
            logger.info("Verifying Residency Requirements section");
            logger.info("Clicking 'No' for Residency");

            // Click "No" option to trigger error
            String noRadioLocator = ResidentYesRadioBtn.replace("<index>", "2");
            Locator noRadio = page.locator(noRadioLocator);
            waitForElement("xpath=" + noRadioLocator, "DEFAULT_WAIT");
            noRadio.click();
            logger.info("Clicked 'No' for Residency");

            // Assert error message is visible
            Locator errorMsg = page.locator(ResidencyErrorMessage);
            waitForElement("xpath=" + ResidencyErrorMessage, "DEFAULT_WAIT");
            if (!errorMsg.isVisible()) {
                throw new AssertionError("Residency error message not displayed after selecting 'No'");
            }
            logger.info("Error message verified successfully for 'No' selection");

        } catch (PlaywrightException e) {
            logger.error("Failed to get error message after selecting 'No': " + e.getMessage());
            throw e;
        }
    }

    public void verifyResidencyRequirementsSelectOptionYES() {
        try {
            logger.info("Verifying Residency Requirements section");

            // Click "Yes" for Residency
            logger.info("Clicking 'Yes' for Residency");
            String yesRadioLocator = ResidentYesRadioBtn.replace("<index>", "1");
            Locator yesRadio = page.locator(yesRadioLocator);
            waitForElement("xpath=" + yesRadioLocator, "DEFAULT_WAIT");
            yesRadio.click();
            logger.info("Clicked 'Yes' for Residency");

            // Fill in County
            Locator countyInput = page.locator(ResidenceCountyIp);
            waitForElement("xpath=" + ResidenceCountyIp, "DEFAULT_WAIT");
            if (countyInput.inputValue().isEmpty()) {
                countyInput.fill("Bartow");
                logger.info("Filled in County of Residence");
            }

            // Randomly select Employment status (Yes - index 1, No - index 2, N/A - index 3)
            logger.info("Selecting Employment status");
            String employmentOption = EmploymentYesRadioBtn.replace("<index>", "2"); // Let's pick 'No' for demo
            Locator employmentRadio = page.locator(employmentOption);
            waitForElement("xpath=" + employmentOption, "DEFAULT_WAIT");
            employmentRadio.click();
            logger.info("Selected 'No' for Employment status");

            // Randomly select Permanent Home status
            logger.info("Selecting Permanent Home status");
            String permHomeOption = PermHomeYesRadioBtn.replace("<index>", "3"); // Let's pick 'N/A'
            Locator permHomeRadio = page.locator(permHomeOption);
            waitForElement("xpath=" + permHomeOption, "DEFAULT_WAIT");
            permHomeRadio.click();
            logger.info("Selected 'N/A' for Permanent Home status");

            // Select Yes for US Citizenship
            logger.info("Selecting US Citizenship status");
            String usCitizenOption = USCitizenYesRadioBtn.replace("<index>", "1"); // Always Yes as per instruction
            Locator usCitizenRadio = page.locator(usCitizenOption);
            waitForElement("xpath=" + usCitizenOption, "DEFAULT_WAIT");
            usCitizenRadio.click();
            logger.info("Selected 'Yes' for US Citizenship status");

            logger.info("Residency requirement verified successfully");

        } catch (PlaywrightException e) {
            logger.error("Failed to verify residency requirements: " + e.getMessage());
            throw e;
        }
    }

    public void validateResidencySection_USCitizenNo() {
        try {
            logger.info("Selecting 'No' for US Citizen to validate error message");

            String usCitizenOption = USCitizenYesRadioBtn.replace("<index>", "1"); // Always Yes as per instruction
            Locator noBtn = page.locator(usCitizenOption);
            waitForElement("xpath=" + usCitizenOption, "DEFAULT_WAIT");
            noBtn.check();

            Locator error = page.locator(USCitizenErrorMessage);
            waitForElement("xpath=" + USCitizenErrorMessage, "DEFAULT_WAIT");

            if (error.isVisible()) {
                logger.info("Error message displayed as expected when 'No' is selected for US Citizenship.");
            } else {
                logger.error("Expected error message NOT displayed for US Citizenship = No");
            }

        } catch (PlaywrightException e) {
            logger.error("Failed to verify US Citizenship 'No' error: " + e.getMessage());
            throw e;
        }
    }

    public void clickMenu(String menuName) {
        Locator menu = page.locator("//span[text()='" + menuName + "']").last();
        menu.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        menu.scrollIntoViewIfNeeded();
        menu.click();
        Locator loadingImage = page.locator("//img[@class='logoCenterLoading']");
        loadingImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        loadingImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

}