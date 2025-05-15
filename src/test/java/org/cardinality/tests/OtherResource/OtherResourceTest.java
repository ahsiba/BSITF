package org.cardinality.tests.OtherResource;

import org.cardinality.base.BaseTest;
import org.cardinality.pages.ApplicantInfoPage;
import org.cardinality.pages.LoginPage;
import org.cardinality.pages.OtherResourcesPage;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.microsoft.playwright.Locator;

public class OtherResourceTest extends BaseTest {

        private static final String URL = "https://gcm-dev.cardinality.info/login";
        private String username = "rmrfmukul@gmail.com"; // Replace with actual username
    private String password = "Mukul@@1997";            // Replace with actual password

    @Test(description = "TC_12 - Verify checkbox functionality and table structure in Other Resources section")
    public void verifyTablesAndCheckboxBehavior() {
        page.navigate(URL); // Replace with actual URL
        LoginPage loginPage = new LoginPage(page);
        Boolean result = loginPage.login(username, password);
        assert result : "Login failed. Please check credentials or page locators.";
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.clickApplicationID();
        applicantInfoPage.clickOnTitleTab("2");
        OtherResourcesPage orPage = new OtherResourcesPage(page);

        // Table section names
        String[] tableNames = {
                "Personal Support Services",
                "Financial & Benefits Resources",
                "Other Resources"
        };

        // 1. Validate whether the 3 tables are displayed with appropriate name
        for (String tableName : tableNames) {
            Assert.assertTrue(
                    orPage.verifyCheckboxColumns(tableName),
                    "Checkboxes should be present for table: " + tableName);
        }

        // 2. Verify all 3 tables have all the 5 columns (15 total headers)
        int headerCount = page.locator(OtherResourcesPage.columnHeaders).count();
        Assert.assertEquals(headerCount, 15, "Total number of column headers should be 15 across 3 tables");

        // 3 & 4. Verify checkboxes exist for 4 specific columns & simulate clicking
        // random checkboxes
        for (String tableName : tableNames) {
            String checkboxLocator = orPage.checkBoxLocator(tableName);
            Locator checkboxes = page.locator(checkboxLocator);
            int totalCheckboxes = checkboxes.count();
            Assert.assertTrue(totalCheckboxes > 0, "Checkboxes should be available in table: " + tableName);

            // Click random 3 checkboxes
            for (int i = 0; i < Math.min(3, totalCheckboxes); i++) {
                checkboxes.nth(i).click();
                Assert.assertTrue(checkboxes.nth(i).isChecked(),
                        "Checkbox " + i + " should be checked in table: " + tableName);
            }

            // 6. Deselect all selected checkboxes
            for (int i = 0; i < totalCheckboxes; i++) {
                if (checkboxes.nth(i).isChecked()) {
                    checkboxes.nth(i).click();
                    Assert.assertFalse(checkboxes.nth(i).isChecked(),
                            "Checkbox " + i + " should be unchecked in table: " + tableName);
                }
            }
        }
    }

    @Test(description = "TC_13 - Verify Notes and RadioButtons fields")
    public void verifyNotesAndRadioButtons() {
        page.navigate(URL); // Replace with actual URL
        LoginPage loginPage = new LoginPage(page);
        Boolean result = loginPage.login(username, password);
        assert result : "Login failed. Please check credentials or page locators.";
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.clickApplicationID();
        applicantInfoPage.clickOnTitleTab("2");

        OtherResourcesPage orPage = new OtherResourcesPage(page);

        // 1. Verify the visibility of all 4 sections and their text

        // a. "Notes" and "Describe your current living situation" -> <label><b>...</b>
        Locator noteDescribeHeaders = page.locator(OtherResourcesPage.noteDescribeSectionHeader);
        Assert.assertTrue(noteDescribeHeaders.locator("text=Notes").isVisible(), "'Notes' section should be visible");
        Assert.assertTrue(noteDescribeHeaders.locator("text=Describe your current living situation").isVisible(),
                "'Describe your current living situation' section should be visible");

        // b. "Where do you live?" and "Who helps you in your daily life?" ->
        // <h6>...</h6>
        Locator whereWhoHeaders = page.locator(OtherResourcesPage.whereWhoSectionHeader);
        Assert.assertTrue(whereWhoHeaders.locator("text=Where do you live?").isVisible(),
                "'Where do you live?' section should be visible");
        Assert.assertTrue(whereWhoHeaders.locator("text=Who helps you in your daily life?").isVisible(),
                "'Who helps you in your daily life?' section should be visible");

        // 2. Verify the Notes textbox is visible and operable
        Locator notesTextbox = page.locator(OtherResourcesPage.notesTextboxLocator);
        Assert.assertTrue(notesTextbox.isVisible(), "Notes textbox should be visible");
        notesTextbox.type("Test text for notes");
        Assert.assertEquals(notesTextbox.inputValue(), "Test text for notes",
                "Text should be entered in Notes textbox");

        // 3. Verify the radio buttons for 'Where do you live?' (9 options)
        Locator whereDoYouLiveRadios = page.locator(OtherResourcesPage.whereDoYouLiveRadioButtonsLocator);
        int totalWhereDoYouLiveRadios = whereDoYouLiveRadios.count();
        Assert.assertEquals(totalWhereDoYouLiveRadios, 9, "There should be 9 radio buttons for 'Where do you live?'");

        // Verify all 9 radio buttons are visible and operable
        for (int i = 0; i < totalWhereDoYouLiveRadios; i++) {
            Locator radioButton = whereDoYouLiveRadios.nth(i);
            Assert.assertTrue(radioButton.isVisible(),
                    "Radio button " + i + " should be visible for 'Where do you live?'");
            radioButton.click();
            Assert.assertTrue(radioButton.isChecked(),
                    "Radio button " + i + " should be checked for 'Where do you live?'");
        }

        // 4. Verify the 'Describe your current living situation' textbox is visible and
        // operable
        Locator livingSituationTextbox = page.locator(OtherResourcesPage.livingSituationTextboxLocator);
        Assert.assertTrue(livingSituationTextbox.isVisible(),
                "'Describe your current living situation' textbox should be visible");
        livingSituationTextbox.type("Test text for living situation");
        Assert.assertEquals(livingSituationTextbox.inputValue(), "Test text for living situation",
                "Text should be entered in 'Describe your current living situation' textbox");

        // 5. Verify the radio buttons for 'Who helps you in your daily life?' (8
        // options)
        Locator whoHelpsYouRadios = page.locator(OtherResourcesPage.whoHelpsYouRadioButtonsLocator);
        int totalWhoHelpsYouRadios = whoHelpsYouRadios.count();
        Assert.assertEquals(totalWhoHelpsYouRadios, 8,
                "There should be 8 radio buttons for 'Who helps you in your daily life?'");

        // Verify all 8 radio buttons are visible and operable
        for (int i = 0; i < totalWhoHelpsYouRadios; i++) {
            Locator radioButton = whoHelpsYouRadios.nth(i);
            Assert.assertTrue(radioButton.isVisible(),
                    "Radio button " + i + " should be visible for 'Who helps you in your daily life?'");
            radioButton.click();
            Assert.assertTrue(radioButton.isChecked(),
                    "Radio button " + i + " should be checked for 'Who helps you in your daily life?'");
        }
    }

}