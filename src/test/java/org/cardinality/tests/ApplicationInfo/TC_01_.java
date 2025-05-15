package org.cardinality.tests.ApplicationInfo;

import org.cardinality.base.BaseTest;
import org.cardinality.pages.ApplicantInfoPage;
import org.testng.annotations.Test;

public class TC_01_ extends BaseTest {

    private static final String URL = "https://gcm-dev.cardinality.info/pages/dynamic-search/search/1196"; // Replace with actual URL

    @Test
    public void viewApplicationButtonPageNavigation() {
        page.navigate(URL); // Replace with actual URL
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.clickStartApplicationButton();
    }

    @Test void verifyDemographics() {
        page.navigate(URL); // Replace with actual URL
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.verifyDemographics();
    }

    @Test void verifyRace() {
        page.navigate(URL); // Replace with actual URL
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.verifyRace();
    }

    @Test void verifyHouseHoldIncomeDropDown() {
        page.navigate(URL); // Replace with actual URL
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.verifyHouseHoldIncome();
    }

    @Test void verifyTrustFundDropDown() {
        page.navigate(URL); // Replace with actual URL
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.verifyTrustFund();
    }

    @Test void fillResidencySection_YesOption() {
        page.navigate(URL); // Replace with actual URL
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.verifyResidencyRequirementsSelectOptionYES();
    }

    @Test void fillResidencySection_NoOption() {
        page.navigate(URL); // Replace with actual URL
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.verifyResidencyRequirementsSelectOptionNO();
    }

    @Test void validateResidencySection_USCitizenNo() {
        page.navigate(URL); // Replace with actual URL
        ApplicantInfoPage applicantInfoPage = new ApplicantInfoPage(page);
        applicantInfoPage.validateResidencySection_USCitizenNo();
    }

}
