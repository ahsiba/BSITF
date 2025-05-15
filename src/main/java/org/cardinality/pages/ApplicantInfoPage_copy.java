// package org.cardinality.pages;

// import com.microsoft.playwright.Locator;
// import com.microsoft.playwright.Page;
// import com.microsoft.playwright.PlaywrightException;
// import com.microsoft.playwright.options.WaitForSelectorState;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// public class ApplicantInfoPage_copy {
//     private final Page page;
//     private static final Logger logger = LoggerFactory.getLogger(ApplicantInfoPage.class);
//     private static final int MAX_RETRY_ATTEMPTS = 3;

//     private static final String StartApplicationButton = "//button[contains(@class, 'btn') and contains(@class, 'btn-primary') and contains(@class, 'ng-star-inserted')]";
//     private static final String ApplicationID ="//div[contains(@class, 'application-number')]/div[1]/p[2]";
//     private static final String ApplicantFirstNameIp="//div[@id='elhkccq']/*[2]";
//     private static final String ApplicantLastNameIp="//div[@id='edqs79']/*[2]";
//     private static final String EmailAddressIp="//div[@id='eyvu8zjk']/*[2]";
//     private static final String DaytimePhoneIp="//div[@id='e170aze']/*[2]";
//     private static final String AlternatePhoneIp="//input[@id='eabqw9i-applicant_info.alt_phone']";
//     private static final String OccupationIp="//input[@id='e2h8ykl-applicant_info.occupation']";
//     private static final String EmployerIp="//input[@id='eog2lod-applicant_info.employer']";
//     private static final String SocialSecurityNumberIp="//lib-ssn-input[@id='ed7op1-applicant_info.ssn']";
//     private static final String GenderIp="//div[@class='choices form-group formio-choices is-disabled']/*[1]";
//     private static final String DateofBirthIp="//div[@class='input-group-append']/*[1]";
//     private static final String StreetAddressIp="//div[@id='e38qv16']/*[2]";
//     private static final String CityIp="//div[@id='e1axdb9']/*[2]";
//     private static final String CountyIp="//div[@id='e5areuj']/*[2]";
//     private static final String StateIp="//div[@id='eb0p6gm']/*[2]";
//     private static final String ZIPCodeIp="//div[@id='ecr8mak']/*[2]";
//     private static final String MailingAddrSameCheckbox="//label[@class='form-check-label']/*[1]";
//     private static final String AddAnotherBtn="";
//     private static final String MailingAddrIp="//span[@class='w-100 p-autocomplete p-component']/*[1]";
//     private static final String MailingAddrCityIp="//input[@id='e0nf53i-applicant_address.city']";
//     private static final String MailingAddrCountryIp="//input[@id='egqzzs-applicant_address.county']";
//     private static final String MailingAddrStateIp="//div[@id='etrmqs']/*[2]";
//     private static final String Zip_code="//input[@id='ehb6ye-applicant_address.zip_code']";
//     private static final String RaceDropDwnSelect="//div[@id='ekrgpg9'][contains(.,'Race')]//div[contains(@class, 'choices') and contains(@class, 'formio-choices')]";
//     private static final String RaceSearchBox="//div[@id='ekrgpg9'][contains(., 'Race')]//input[contains(@class, 'choices__input--cloned')]";
//     private static final String RaceDropDwnList="//div[@id='ekrgpg9'][contains(., 'Race')]//div[contains(@class, 'choices__list')]/div[contains(@class, 'choices__item')][1][not(contains(text(), 'Select'))]";
//     private static final String IncomeSrcDropDwnSelect="";
//     private static final String IncomeSrcDropDwnList="";
//     private static final String IncomeSrcDropDwnSearchBox="";
//     private static final String WeeklyIncomeValueIp="//input[@id='e01xugq-applicant_info.household_income_price']";
//     private static final String TrustFundDropDwnSelect="";
//     private static final String TrustFundDropDwnList="";
//     private static final String TrustFundDropDwnSearchBox="";
//     private static final String ResidentYesRadioBtn="(//input[starts-with(@id, 'e3zpb7n-e4ysyl3--')])[<index>]";
//     private static final String ResidenceCountryIp="//input[@id='en9oufh-applicant_residency.county_of_residence']";
//     private static final String EmploymentYesRadioBtn="(//input[starts-with(@id, 'e3zpb7n-ei0xo1h--')])[<index>]";
//     private static final String PermHomeYesRadioBtn="(//input[starts-with(@id, 'e3zpb7n-exsdm1j--')])[1]";
//     private static final String USCitizenYesRadioBtn="(//input[starts-with(@id, 'e3zpb7n-e9qkgpt--')])[1]";
//     private static final String ApplCompletedByDropDwnSelect="//div[@id='exnum8t'][contains(., 'Application Completed By')]//div[contains(@class, 'form-control') and contains(@class, 'dropdown')]";
//     private static final String ApplCompletedByDropDwnListn="//div[@id='exnum8t'][contains(., 'Application Completed By')]//div[contains(@class, 'choices__list')]/div[contains(@class, 'choices__item')][1]";
//     private static final String ApplCompletedByDropDwnSearchBox="//div[@id='exnum8t'][contains(., 'Application Completed By')]//input[contains(@class, 'choices__input--cloned')]";
//     private static final String ApplCompletedByNameIp="//input[@id='e0qi7si-applicant_relation.name']";
//     private static final String ApplCompletedByMailingAddrIp="";
//     private static final String ApplCompletedByCityIp="//input[@id='epx3smq-applicant_relation.city']";
//     private static final String ApplCompletedByCountryIp="//input[@id='epx3smq-applicant_relation.county']";
//     private static final String ApplCompletedByStateIp="//input[@id='ez96kr4-applicant_relation.state']";
//     private static final String ApplCompletedByZipCodeIp="//input[@id='epemj98-applicant_relation.zip_code']";
//     private static final String ApplCompletedByDaytimePhoneIp="//input[@id='emmqhx-applicant_relation.alt_phone']";
//     private static final String ApplCompletedByEmailAddrIp="//input[@id='evuqhul-applicant_relation.email_address']";
//     private static final String ApplCompletedByRelationIp="//input[@id='esm4g9i-applicant_relation.relation']";
//     private static final String NextBtn="//button[contains(text(), 'Next')]";
//     private static final String BackToDashboardBtn="//button[contains(text(), 'Back to Dashboard')]";
//     private static final String ApplicationInfoBtn="(//button[contains(@class, 'stepper')])[1]";
//     private static final String OtherResourcesBtn="(//button[contains(@class, 'stepper')])[2]";
//     private static final String InjuryBtn="(//button[contains(@class, 'stepper')])[3]";
//     private static final String RequestBtn="";
//     private static final String Error="//div[@id='e-e3abzu-applicant_residency.resident_of_ga']";
//     private static final String DropDownSelects="(//div[contains(@class, 'form-control') and contains(@class, 'dropdown')])[<index>]";
//     private static final String DropDownSearchBar="(//input[contains(@class, 'choices__input--cloned')])[<index>]";


//     public ApplicantInfoPage(Page page) {
//         this.page = page;
//     }

//     private final String reportedByLocator = "//textarea[contains(@placeholder,'Reported By')]";

//     private Locator heightInFeetDropdown, heightInInchesDropdown, weight, eyeColorDropdown, hairColorDropdown,
//             hairStyle, facialHair, complexionDropdown, bodyBuild, reportedBy, typeDropdown, bodyPartDropdown,
//             sideDropdown, description, addImage, addPhysicalCharacteristics;

//     public void clickMenu(String menuName) {
//         Locator menu = page.locator("//span[text()='" + menuName + "']").last();
//         menu.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         menu.scrollIntoViewIfNeeded();
//         menu.click();
//         Locator loadingImage = page.locator("//img[@class='logoCenterLoading']");
//         loadingImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         loadingImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
//     }

//     // Click Save button
//     public void clickSave() {
//         Locator saveButton = page.locator("//button[@class='btn btn-primary btn-md d-inline-flex']");
//         saveButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         saveButton.click();
//     }

//     // Click Cancel button
//     public void clickCancel() {
//         Locator cancelButton = page.locator("//button[contains(@class,'btn btn-secondary btn')]");
//         cancelButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         cancelButton.click();
//     }

//     // Check Glasses checkbox
//     public void checkGlasses() {
//         Locator glasses = page.locator("//span[text()='Glasses/Contacts']");
//         glasses.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         glasses.click();
//     }

//     // Verify if submission was successful
//     public boolean isSubmissionSuccessful() {
//         Locator successMessage = page.locator("//div[text()=' Submitted Successfully ']");
//         successMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         return successMessage.isVisible();
//     }

//     // Check Braces checkbox
//     public void checkBraces() {
//         Locator braces = page.locator("//span[text()='Braces ']");
//         braces.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         braces.click();
//     }

//     // Edit Feet selection
//     public void editFeetDropdown(String feetValue) {
//         page.locator("(//div[@aria-selected='true'])[1]").click();
//         Locator value = page.locator("(//div[@role='listbox']//div[@data-value='" + feetValue + "'])[1]");
//         value.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         value.scrollIntoViewIfNeeded();
//         value.click();
//     }

//     public void editInchesDropdown(String feetInches) {
//         final int maxAttempts = 3;
//         logger.info("Setting dropdown value to: {}", feetInches);

//         for (int attempt = 0; attempt < maxAttempts; attempt++) {
//             try {
//                 // 1. Original dropdown trigger XPath exactly as you provided
//                 Locator dropdownTrigger = page.locator("(//div[@aria-selected='true'])[3]");
//                 dropdownTrigger.scrollIntoViewIfNeeded();
//                 dropdownTrigger.click(new Locator.ClickOptions().setForce(true));

//                 // 2. Original option XPath exactly as you provided
//                 Locator option = page.locator("//div[@role='listbox']//div[@data-value='" + feetInches + "']")
//                         .last();

//                 option.scrollIntoViewIfNeeded();
//                 option.click(new Locator.ClickOptions().setForce(true));

//                 // 3. Verification using original XPath
//                 Locator selectedValue = page.locator("(//div[@aria-selected='true'])[3]");
//                 String actualValue = selectedValue.getAttribute("data-value");

//                 if (feetInches.equals(actualValue)) {
//                     logger.info("Successfully set dropdown to: {}", feetInches);
//                     return;
//                 }

//             } catch (Exception e) {
//                 logger.warn("Attempt {} failed: {}", attempt + 1, e.getMessage());

//                 if (attempt == maxAttempts - 1) {
//                     throw new RuntimeException(
//                             String.format("Failed to set dropdown to '%s' after %d attempts",
//                                     feetInches, maxAttempts),
//                             e);
//                 }

//                 // Re-locate elements for next attempt
//                 page.locator("(//div[@aria-selected='true'])[3]").click(new Locator.ClickOptions().setForce(true));
//             }
//         }
//     }

//     public boolean isGlassesChecked() {
//         return page.locator("//input[@checked = 'true' and @placeholder='Enter Glasses Flag']").isChecked();
//     }

//     public boolean isBracesChecked() {
//         return page.locator("//input[@checked = 'true' and @placeholder='Enter Braces Flag']").isChecked();
//     }

//     public void waitForLoaderToDisappear() {
//         Locator loadingImage = page.locator("//img[@class='logoCenterLoading']");
//         try {
//             loadingImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(5000)); // 5
//                                                                                                                        // seconds
//                                                                                                                        // timeout
//             logger.info("Loader has disappeared.");
//         } catch (Exception e) {
//             logger.info("Loader did not appear or already disappeared. Proceeding without waiting.");
//         }
//     }

//     public void fillReportedBy(String reportedByValue) {
//         logger.info("Filling 'Reported By' field with value: {}", reportedByValue);
//         reportedBy.fill(reportedByValue);
//     }

//     public String getReportedBy() {
//         logger.info("Retrieving value from 'Reported By' field");
//         reportedBy.waitFor();
//         return reportedBy.inputValue();
//     }

//     public void fillFacialHair(String facialHairValue) {
//         logger.info("Filling 'Facial Hair' field with value: {}", facialHairValue);
//         facialHair.fill(facialHairValue);
//     }

//     public String getFacialHair() {
//         logger.info("Retrieving value from 'Facial Hair' field");
//         facialHair.waitFor();
//         return facialHair.inputValue();
//     }

//     public void fillBodyBuild(String bodyBuildValue) {
//         logger.info("Filling 'Body Build' field with value: {}", bodyBuildValue);
//         bodyBuild.fill(bodyBuildValue);
//     }

//     public void fillWeight(String weightValue) {
//         logger.info("Filling 'Weight' field with value: {}", weightValue);
//         weight.fill(weightValue);
//     }

//     public String getWeight() {
//         logger.info("Retrieving value from 'Weight' field");
//         weight.waitFor();
//         return weight.inputValue();
//     }

//     public void fillHairStyle(String hairStyleValue) {
//         logger.info("Filling 'Hair Style' field with value: {}", hairStyleValue);
//         hairStyle.fill(hairStyleValue);
//     }

//     public String getHairStyle() {
//         logger.info("Retrieving value from 'Hair Style' field");
//         hairStyle.waitFor();
//         return hairStyle.inputValue();
//     }

//     public void fillTextArea(String facialHairValue, String bodyBuildValue, String weightValue,
//             String hairStyleValue, String reportedByValue) {
//         logger.info("Starting to fill text area fields.");
//         fillFacialHair(facialHairValue);
//         fillBodyBuild(bodyBuildValue);
//         fillWeight(weightValue);
//         fillHairStyle(hairStyleValue);
//         fillReportedBy(reportedByValue);
//         logger.info("All text area fields have been filled.");
//     }

//     public void selectDropdown(String placeholder, String dropdownValue) {
//         logger.info("Selecting dropdown value: {} for placeholder: {}", dropdownValue, placeholder);

//         Locator dropdown = page.locator(String.format(
//                 "//select[contains(@placeholder, '%s')]//parent::div[contains(@class, 'dropdown')]", placeholder))
//                 .last();

//         for (int attempt = 0; attempt < MAX_RETRY_ATTEMPTS; attempt++) {
//             try {
//                 logger.info("Attempt {} to select dropdown value: {}", attempt + 1, dropdownValue);

//                 dropdown.scrollIntoViewIfNeeded();
//                 dropdown.click(new Locator.ClickOptions().setForce(true));

//                 Locator option = page.locator(String.format("//div[text()='%s']", dropdownValue)).last();

//                 option.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//                 option.scrollIntoViewIfNeeded();
//                 option.click(new Locator.ClickOptions().setForce(true));

//                 logger.info("Successfully selected dropdown value: {}", dropdownValue);
//                 return; // Success, exit the method

//             } catch (PlaywrightException e) {
//                 logger.warn("Attempt {} failed to select dropdown value: {}", attempt + 1, dropdownValue);
//             }
//         }
//         String errorMessage = String.format("Failed to select dropdown value: %s after %d attempts", dropdownValue,
//                 MAX_RETRY_ATTEMPTS);
//         logger.error(errorMessage);
//         throw new RuntimeException(errorMessage);
//     }

//     public void selectTypeDropdown(String typeDropdownValue) {
//         logger.info("Selecting dropdown value: {} for 'Type' dropdown", typeDropdownValue);
//         selectDropdown("Type", typeDropdownValue);
//     }

//     public String getTypeDropdown() {
//         logger.info("Retrieving value from 'Type Dropdown' field");
//         typeDropdown.waitFor();
//         String rawText = typeDropdown.innerText().trim();
//         return rawText.replace("Remove item", "").trim();
//     }

//     public String getBodyPartDropdown() {
//         logger.info("Retrieving value from 'Body Part Dropdown' field");
//         bodyPartDropdown.waitFor();
//         String rawText = bodyPartDropdown.innerText().trim();
//         return rawText.replace("Remove item", "").trim();
//     }

//     public void selectSideDropdown(String sideDropdownValue) {
//         logger.info("Selecting dropdown value: {} for 'Side' dropdown", sideDropdownValue);
//         selectDropdown("Side", sideDropdownValue);
//     }

//     public String getSideDropdown() {
//         logger.info("Retrieving value from 'Body Part Dropdown' field");
//         sideDropdown.waitFor();
//         String rawText = sideDropdown.innerText().trim();
//         return rawText.replace("Remove item", "").trim();
//     }

//     public void fillDescription(String fillDescripiton) {
//         logger.info("Filling value on the 'Description' field");
//         description.fill(fillDescripiton);
//     }

//     public String getDescriptionValue() {
//         logger.info("Retrieving value from 'description' field");
//         description.waitFor();
//         return description.inputValue();
//     }

//     public void editEyeColorDropdown(String eyeColorDropdownValue) {
//         logger.info("Editing dropdown value: {} for 'Eye Color' dropdown", eyeColorDropdownValue);
//         Locator removeEyeColorButton = page.locator(
//                 "//select[contains(@id, 'eye_color_code')]/following-sibling::div" +
//                         "//div[contains(@class,'choices__item')]//button[contains(@class,'choices__button')]");
//         removeEyeColorButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         removeEyeColorButton.click();
//         selectDropdown("Eye Color", eyeColorDropdownValue);
//     }

//     public void selectFeetDropdown(String feetValue) {
//         logger.info("Selecting dropdown value: {} for 'Feet' dropdown", feetValue);
//         page.locator("//div[@data-value='Select Feet']").click();
//         Locator value = page.locator("(//div[@role='listbox']//div[@data-value='" + feetValue + "'])[1]");
//         value.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         value.scrollIntoViewIfNeeded();
//         value.click();
//     }

//     public String getSelectedFeetDropdown() {
//         String raw = page.locator("(//div[@aria-selected='true'])[1]").innerText().trim();
//         return raw.replace("Remove item", "").trim();
//     }

//     // Select Inches value
//     public void selectInchesDropdown(String inchValue) {
//         logger.info("Selecting dropdown value: {} for 'Inches' dropdown", inchValue);

//         Locator dropdown = page.locator("//div[@data-value='Select Inches']");
//         dropdown.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         dropdown.scrollIntoViewIfNeeded();
//         dropdown.click();

//         Locator inchOption = page.locator("//div[@data-value='" + inchValue + "']").last();

//         int retryCount = 3;
//         for (int i = 0; i < retryCount; i++) {
//             try {
//                 inchOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//                 inchOption.scrollIntoViewIfNeeded();
//                 inchOption.click();
//                 logger.info("Successfully selected dropdown value: {}", inchValue);
//                 return; // Exit the method if successful
//             } catch (Exception e) {
//                 logger.warn("Attempt {} failed: {}", i + 1, e.getMessage());
//                 if (i < retryCount - 1) {
//                     dropdown.click(new Locator.ClickOptions().setForce(true)); // forceful click retry
//                 }
//             }
//         }
//         logger.error("Failed to select dropdown value: {} after {} attempts", inchValue, retryCount);
//         throw new RuntimeException("Failed to select dropdown value: " + inchValue);
//     }

//     public String getSelectedInchesDropdown() {
//         String raw = page.locator("(//div[@aria-selected='true'])[3]").innerText().trim();
//         return raw.replace("Remove item", "").trim();
//     }
// }