package org.cardinality.pages;

import java.util.ArrayList;
import java.util.List;

import org.cardinality.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class TimelinePage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(TimelinePage.class);

    public TimelinePage(Page page) {
        super(page);
    }

    // String locators
    private static final String RIBBON_WORKERS = "//button[text() = ' Workers ']";
    private static final String SAMPLE="";
    private static final String Application_status="//p[starts-with(normalize-space(), 'Application status:')]"; 
    private static final String Application_ID="//b[text()='Application ID']//parent::p//following-sibling::p";


    public void applicationStatusVerify(){
        Locator StatusLocator=page.locator(Application_ID);
        String Status=StatusLocator.textContent();
        System.out.println(Status);
        if (Status!=null && Status.contains("Submitted")){
            System.out.println("Application Submitted");
        }   
    }
    public void applicationID(){
        Locator IDLocator=page.locator(Application_ID);
        String ApplicationID=IDLocator.textContent();
        System.out.println(ApplicationID);
    }

    public void selectName() {
        try {
            waitForElement(SAMPLE, "SHORT_WAIT");
            page.click(SAMPLE);

            // Wait for the choices list to be visible
            waitForElement(SAMPLE, "SHORT_WAIT");

            // Select the option that matches the provided name
            //String nameOptionXPath = String.format("%s[text()='%s']", CHOICES_LIST_OPTION, name);
            page.click(SAMPLE + "[2]");
            String str = page.locator(SAMPLE).innerText();
            logger.info("Selected name: " + str);
            String styr2 = page.locator(SAMPLE).getAttribute("value");
            logger.info("Selected name: " + styr2);

            logger.info("Selected name: ");
        } catch (Exception e) {
            logger.error("Failed to select the name: ", e);
        }
    }

    public void selectRole() {
        waitForElement(SAMPLE, "SHORT_WAIT");
        page.click(SAMPLE);
        // Wait for the choices list to be visible
        waitForElement(SAMPLE, "SHORT_WAIT");

        // Select the first option from the choices list
        page.click(SAMPLE + "[2]");
    }
    
    public void checkNamesAscendingOrder() {
        Locator nameColumn = page.locator(SAMPLE);
        List<String> names = new ArrayList<>();

        // Extract names from the column
        nameColumn.allInnerTexts().forEach(names::add);

        // Check if names are in ascending order
        for (int i = 1; i < names.size(); i++) {
            Assert.assertTrue(names.get(i - 1).compareTo(names.get(i)) <= 0, "Names are not in ascending order.");
            logger.info("Names are in ascending order.");
        }
        logger.info("Names are in ascending order.");
    }  
    
    public void selectPaginationValue(String value) {
        // Click the dropdown
        page.click(SAMPLE);
        logger.info("Clicked on pagination dropdown.");

        // Select the value based on the input parameter
        String paginationValueLocator = String.format(SAMPLE, value);
        Locator paginationValue = page.locator(paginationValueLocator);
        paginationValue.click();
        logger.info("Selected pagination value: " + value);
    }
    
    public void verifyEffectiveDate() {
        // Get today's date and time in the required format
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String expectedDate = now.format(formatter);
    
        // Locate the input field and get its value
        Locator effectiveDateInput = page.locator(SAMPLE);
        String actualDate = effectiveDateInput.getAttribute("value");
    
        // Convert expected and actual dates to LocalDateTime for comparison
        LocalDateTime expectedDateTime = LocalDateTime.parse(expectedDate, formatter);
        LocalDateTime actualDateTime = LocalDateTime.parse(actualDate, formatter);
    
        // Allow a tolerance of a few seconds for the time difference
        long toleranceInSeconds = 5;
        boolean isWithinTolerance = Math.abs(Duration.between(expectedDateTime, actualDateTime).getSeconds()) <= toleranceInSeconds;
    
        // Verify the date using assertion
        Assert.assertTrue( isWithinTolerance,"Effective date is not correctly auto-populated.");
        logger.info("Effective date is correctly auto-populated with today's date: " + actualDate);
    }
}
    

    
    


