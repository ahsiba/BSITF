package org.cardinality.pages;

import java.nio.file.Paths;

import org.cardinality.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BSITFDocumentsPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BSITFDocumentsPage.class);
    private static final String residencyRequirementsExpand_Collapse = "//a[@id='pn_id_8_header_action']";
    private static final String documentsExpand_Collapse = "//a[@id='pn_id_9_header_action']";
    private static final String searchBtn = "(//input[@placeholder='Search'])[<index>]";
    private static final String filterSearchBtn = "//div[@class='dx-item dx-menu-item dx-menu-item-has-icon dx-menu-item-has-submenu dx-menu-item-expanded']";
    private static final String columnChooser_exportBtn = "";
    private static final String proofOfGeorgiaResidency = "//div[@aria-label='dropdown trigger']";
    private static final String FilterForCategory = "//span[@aria-label=\"Show filter options for column 'Category'\"]";
    private static final String FilterForDocumentStatus = "//span[@aria-label=\"Show filter options for column 'Document Status'\"]";
    private static final String FilterForSubCategory = "//span[@aria-label=\"Show filter options for column 'Sub Category'\"]";
    private static final String FilterForDocumentName = "//span[@aria-label=\"Show filter options for column 'Document Name'\"]";
    private static final String FilterForDocumentType = "//span[@aria-label=\"Show filter options for column 'Document Type'\"]";
    private static final String FilterForUploadedBy = "(//span[@aria-label=\"Show filter options for column 'Uploaded By'\"])[<index>]";
    private static final String FilterForUploadedDate = "(//span[@aria-label=\"Show filter options for column 'Uploaded Date'\"])[<index>]";
    private static final String FilterForReviewedBy = "(//span[@aria-label=\"Show filter options for column 'Reviewed By'\"])[<index>]";
    private static final String downloadBtn = "(//a[@class='mat-mdc-tooltip-trigger clsActionBtn ng-star-inserted'])";
    private static final String uploadDocBtn = "//span[@class='p-button-icon pi pi-cloud-upload']";
    private static final String georgiaDropdown = "//td[contains(text(),'Georgia Residency')]//following-sibling::td//div[@aria-label='dropdown trigger']";
    private static final String georgiaUploadBtn = "//td[contains(text(),'Georgia Residency')]//following-sibling::td//div[contains(@class,'clsTableActionIcons')]";
    private static final String CitizenshipUplodBtn = "//td[contains(text(),'Citizenship')]//following-sibling::td//div[contains(@class,'clsTableActionIcons')]";
    private static final String medicalDocumenttion = "//td[contains(text(),'Medical Documentation')]//following-sibling::td//div[contains(@class,'clsTableActionIcons')]";

    public BSITFDocumentsPage(Page page) {
        super(page);
    }

    // String locators
    private static final String RIBBON_POPULATION_GRP = "//button[text() = ' Population Group ']";
    private static final String FilePath = "DocumentsUpload\\SamplePicture.png";
    private static final String documents = "(//div[contains(@class,'p-accordion-tab')])[2]//a[@role='button']";
    private static final String requiredDocu="//div[contains(text(),'Required documents')]";

    public void openDocuments() {
        Locator documentElement = page.locator(documents);
        String DocumentArrowCheck = documentElement.getAttribute("aria-expanded");
        System.out.println(DocumentArrowCheck);
        if (DocumentArrowCheck == "false") {
            page.locator(documents).click();
        } else {
            System.out.println("Documents Already open");
        }
    }
    public void checkDocumentOpen(){
        boolean docuVisible=page.locator(requiredDocu).isVisible();
        if (docuVisible){
            System.out.println("Documet Dropdown is Open");
        }else {
            System.out.println("Document Dropdown is not Open");
        }
    }

    public void georgiaResidencyProof(String proof) {//property file
        page.locator(georgiaDropdown).click();
        page.locator("//ul[@role='listbox']//span[contains(text(),'" + proof + "')]").click();
        page.wait(1000);
    }

    public void uploadDocumentForGeorgiaResidency() {
        page.locator(georgiaUploadBtn).click();
        page.locator("//div[@class='drobordrag']").setInputFiles(paths.get(FilePath));
        page.wait(1000);
        page.locator("//span[text()='Save']").click();

        //Assertion :: Upload
    }

    public void uploadDocumentForCitizenship() {
        page.locator(CitizenshipUplodBtn).click();
        page.locator("//div[@class='drobordrag']").setInputFiles(paths.get(FilePath));
        page.wait(1000);
        page.locator("//span[text()='Save']").click();
    }

    public void uploadDocumentForMedicalDocumentation() {
        page.locator(medicalDocumenttion).click();
        page.locator("//div[@class='drobordrag']").setInputFiles(paths.get(FilePath));
        page.wait(1000);
        page.locator("//span[text()='Save']").click();
    }

}