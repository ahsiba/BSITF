package org.cardinality.tests.BSITFDocuments;
import org.cardinality.base.BaseTest;
import org.cardinality.pages.BSITFDocumentsPage;
import org.cardinality.pages.ApplicantInfoPage;
import org.testng.annotations.Test;

public class TC_01_DocumentVerification extends BaseTest {

    private static final String URL = "https://gcm-dev.cardinality.info/pages/dynamic-routing/tab/1366/193/custom/document/1348"; // Replace with actual URL

    @Test
    public void viewApplicationButtonPageNavigation() {
        page.navigate(URL); 
        BSITFDocumentsPage bSITFDocumentsPage = new BSITFDocumentsPage(page);
        ApplicantInfoPage.clickStartApplicationButton();

        bSITFDocumentsPage.openDocuments();
       bSITFDocumentsPage.checkDocumentOpen();
        bSITFDocumentsPage.georgiaResidencyProof("Utility Bill");
        bSITFDocumentsPage.uploadDocumentForGeorgiaResidency();
        bSITFDocumentsPage.uploadDocumentForCitizenship();
        bSITFDocumentsPage.uploadDocumentForMedicalDocumentation();
    }
}