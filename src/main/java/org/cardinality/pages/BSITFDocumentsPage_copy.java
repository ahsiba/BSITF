// package org.cardinality.pages;

// import org.cardinality.base.BasePage;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import com.microsoft.playwright.Locator;
// import com.microsoft.playwright.Page;

// public class BSITFDocumentsPage extends BasePage {
//     private static final Logger logger = LoggerFactory.getLogger(BSITFDocumentsPage.class);
//     private static final String residencyRequirementsExpand_Collapse="//a[@id='pn_id_8_header_action']";
//     private static final String documentsExpand_Collapse="//a[@id='pn_id_9_header_action']";
//     private static final String searchBtn ="(//input[@placeholder='Search'])[<index>]";
//     private static final String filterSearchBtn="//div[@class='dx-item dx-menu-item dx-menu-item-has-icon dx-menu-item-has-submenu dx-menu-item-expanded']";
//     private static final String columnChooser_exportBtn="";
//     private static final String proofOfGeorgiaResidency="//div[@aria-label='dropdown trigger']";
//     private static final String FilterForCategory="//span[@aria-label=\"Show filter options for column 'Category'\"]";
//     private static final String FilterForDocumentStatus="//span[@aria-label=\"Show filter options for column 'Document Status'\"]";
//     private static final String FilterForSubCategory="//span[@aria-label=\"Show filter options for column 'Sub Category'\"]";
//     private static final String FilterForDocumentName="//span[@aria-label=\"Show filter options for column 'Document Name'\"]";
//     private static final String FilterForDocumentType="//span[@aria-label=\"Show filter options for column 'Document Type'\"]";
//     private static final String FilterForUploadedBy="(//span[@aria-label=\"Show filter options for column 'Uploaded By'\"])[<index>]";
//     private static final String FilterForUploadedDate="(//span[@aria-label=\"Show filter options for column 'Uploaded Date'\"])[<index>]";
//     private static final String FilterForReviewedBy="(//span[@aria-label=\"Show filter options for column 'Reviewed By'\"])[<index>]";
//     private static final String downloadBtn ="(//a[@class='mat-mdc-tooltip-trigger clsActionBtn ng-star-inserted'])";
//     private static final String uploadDocBtn="//span[@class='p-button-icon pi pi-cloud-upload']";

//     public BSITFDocumentsPage(Page page) {
//         super(page);
//     }

//     // String locators
//     private static final String RIBBON_POPULATION_GRP = "//button[text() = ' Population Group ']";
//     private static final String SAMPLE = "//";

//     // Method to update BSITF information basic details
//     public int searchAndNavigateToBSITFNavigator(String keyword) {
//         Locator locator = page.locator(SAMPLE);
//         logger.info("Clicking and filling the search tab with keyword: " + keyword);
//         page.click(SAMPLE);
//         page.fill(SAMPLE, keyword);
//         int count = locator.count();
//         locator.nth(0).click();
//         logger.info("Waiting for the " + keyword + " ribbon to be visible.");
//         waitForElement(RIBBON_POPULATION_GRP, "LONG_WAIT");
//         return count;
//     }

//     // Method to check if the success message is present
//     public boolean isSubmitSuccessMessagePresent() {
//         return page.isVisible(SAMPLE);
//     }

//     public void setStartDate() {
//         logger.info("Setting the start date.");
//         waitForElement(SAMPLE, "SHORT_WAIT");
//         page.click(SAMPLE);
//         waitForElement(SAMPLE, "SHORT_WAIT");
//         page.click(SAMPLE);
//         logger.info("Start date set to today.");
//     }

//     public void clickElement(Page page, String selector) {
//         for (int i = 0; i < 3; i++) {
//             try {
//                 page.evaluate("selector => { " +
//                         "const element = document.evaluate(selector, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; "
//                         +
//                         "if (element) { " +
//                         "element.scrollIntoView(); " +
//                         "const rect = element.getBoundingClientRect(); " +
//                         "const x = rect.left + (rect.width / 2); " +
//                         "const y = rect.top + (rect.height / 2); " +
//                         "const clickEvent = new MouseEvent('click', { " +
//                         "view: window, " +
//                         "bubbles: true, " +
//                         "cancelable: true, " +
//                         "clientX: x, " +
//                         "clientY: y " +
//                         "}); " +
//                         "element.dispatchEvent(clickEvent); " +
//                         "} " +
//                         "}", selector);
//                 break; // Exit loop if click is successful
//             } catch (Exception e) {
//                 logger.warn("Attempt " + (i + 1) + " to click element failed.");
//                 page.waitForTimeout(500); // Wait before retrying
//             }
//         }
//     }

//     public void clickSaveButton() {
//         logger.info("Waiting for the SAVE button.");
//         waitForElement(SAMPLE, "SHORT_WAIT");
//         logger.info("Clicking the SAVE button.");
//         page.click(SAMPLE);
//         logger.info("SAVE button clicked.");
//         waitForElement(SAMPLE, "LONG_WAIT");
//     }

//     public boolean isDeleteSuccessMessagePresent() {
//         // waitForElement(DELETE_SUCCESS_MESSAGE, "SHORT_WAIT");
//         return page.isVisible(SAMPLE);
//     }

// }