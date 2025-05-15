package org.cardinality.pages;

import java.util.*;

import org.cardinality.base.BasePage;
import org.cardinality.components.TextBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class NotesPage extends BasePage {
	private static final Logger logger = LoggerFactory.getLogger(NotesPage.class);
	private static final String textArea="";
	private static final String microphoneBtn="";
	private static final String saveNotesBtn="//button[normalize-space()='Save Notes']";
	private final TextBox textBox = new TextBox(page, "(//label[text() = 'BSITF Number']//following-sibling::input[@id = 'quickSearchBSITFNumber'])[1]");
	private static final String SAMPLE="";
	private static final String textBoxArea="//div[contains(@data-placeholder,'Notes')]";

	public NotesPage(Page page) {
		super(page);

	}

	public TextBox getBSITFNumbTextBox() {
		return textBox;
	}
	public void enterMaximumText(){
		page.locator(textBoxArea).click();
		String RepeatedText="A".repeat(500);
		page.wait(1000);
		page.locator(textBoxArea).fill(RepeatedText);
	}
	public void saveNotesBtn(){
		page.locator(saveNotesBtn).click();
	}

	public void selectSex(String gender) {
		logger.info("Starting to select gender: {}", gender);
		logger.debug("Clicking the sex dropdown/button");
		page.locator(SAMPLE).click();
		logger.debug("Locating the gender option for: {}", gender);
		Locator genderOption = page.locator("//li[@aria-label='" + gender + "']");
		logger.debug("Waiting for the gender option to be visible");
		genderOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		logger.debug("Clicking the gender option: {}", gender);
		genderOption.click();
		logger.info("Successfully selected gender: {}", gender);
	}

	public void selectDob(String targetYear, String targetMonth, String targetDate) {
		// Click the date picker button
		page.locator("(//button[contains(@class,'p-element p-ripple p-datepicker')])[1]").click();

		// Click the year selection button
		page.locator("//div[contains(@class,'p-datepicker-title')]//button[contains(@class,'p-datepicker-year')]").click();

		// Get the current displayed decade range (e.g., "2000 - 2009")
		String decadeText = page.locator("//span[contains(@class,'p-datepicker-decade')]").textContent();
		int currentDecadeStart = Integer.parseInt(decadeText.split(" - ")[0]);
		int targetYearInt = Integer.parseInt(targetYear);

		// Click back until we reach the correct decade
		while (currentDecadeStart > targetYearInt) {
			Locator prevButton = page.locator("//button[contains(@class,'p-datepicker-prev')]");
			if (prevButton.isVisible()) {
				prevButton.click();
				// Wait for the decade text to update
				page.locator("//span[contains(@class,'p-datepicker-decade')]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
				decadeText = page.locator("//span[contains(@class,'p-datepicker-decade')]").textContent();
				currentDecadeStart = Integer.parseInt(decadeText.split(" - ")[0]);
			} else {
				System.out.println("Previous button is not visible!");
				break;
			}
		}

		// Click on the dynamically provided year
		page.locator("//div[contains(@class,'p-yearpicker')]//span[contains(text(),'" + targetYear + "')]").click();

		// Click on the dynamically provided month
		page.locator("//div[contains(@class, 'p-monthpicker')]//span[contains(text(),'" + targetMonth + "')]").click();

		// Click on the dynamically provided date
		Locator dateElement = page.locator("(//div[contains(@class, 'p-datepicker-group')]//table[contains(@class, 'p-datepicker-calendar')]//td[normalize-space()='" + targetDate + "'])[1]");
		dateElement.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)); // Ensure it's visible
		dateElement.evaluate("el => el.scrollIntoView({behavior: 'smooth', block: 'center'})");  // Scroll smoothly
		dateElement.click(new Locator.ClickOptions().setForce(true));  // Force click if needed
	}

	// Method to validate search results with header
	public void validateSearchResultsWithHeader() {
		logger.info("Validating search results with header.");
		// List of keywords to search and their corresponding ribbon locators
		Map<String, String> KEYWORD_TO_RIBBON_MAP = new HashMap<String, String>() {
			{
				put("BSITF Information", SAMPLE);
				put("Alerts", SAMPLE);
				put("Persons", SAMPLE);
				put("Population Group", SAMPLE);
			}
		};
		for (Map.Entry<String, String> entry : KEYWORD_TO_RIBBON_MAP.entrySet()) {
			String keyword = entry.getKey();
			String ribbonLocator = entry.getValue();
			logger.info("Validating search result for keyword: " + keyword);
			validateSearchResult(keyword, ribbonLocator);
		}
	}

	// Helper method to validate search results
	private void validateSearchResult(String keyword, String ribbonLocator) {
		Locator locator = page.locator(SAMPLE);
		page.click(SAMPLE);
		page.fill(SAMPLE, keyword);
		int count = locator.count();
		logger.info("Number of sidebar elements: " + count);
		locator.nth(0).click();
		logger.info("Waiting for the ribbon locator to be visible.");
		waitForElement(ribbonLocator, "LONG_WAIT");
	}

	public List<String> getSearchResults(Integer columnnumber) {
		page.waitForSelector("(//tbody)[3]//td[@aria-colindex='" + columnnumber + "']");
		Locator input = page.locator("(//tbody)[3]//td[@aria-colindex='" + columnnumber + "']");
		input.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		List<String> values = input.allInnerTexts();
		return values != null ? values : Collections.emptyList();
	}

	public void searchByLastName(String lastName) {
		try {
			logger.info("Attempting to search by last name: {}", lastName);
			page.fill(SAMPLE, lastName);
			logger.debug("Filled last name field");
			page.click(SAMPLE);
			logger.debug("Clicked search button");
			logger.info("Search completed successfully for last name: {}", lastName);
			} catch (Exception e) {
				logger.error("Failed to search by last name: {}", lastName, e);
				throw new RuntimeException("Search by last name failed", e);
			}
		}

	public void sortColumn(String columnNumber) {
		String headerLocator = String.format(SAMPLE, columnNumber);
		page.locator(headerLocator).click();
	}

	public List<String> getColumnValues(String columnNumber) {
		try {
			logger.info("Retrieving values for column: {}", columnNumber);
			String selector = String.format(SAMPLE, columnNumber);

			page.waitForSelector(selector, new Page.WaitForSelectorOptions()
						.setState(WaitForSelectorState.VISIBLE)
						.setTimeout(30000));
			logger.debug("Column values located and visible");

			List<String> values = page.locator(selector).allInnerTexts();
			logger.info("Successfully retrieved {} values for column: {}", values.size(), columnNumber);
			return values;
		} catch (Exception e) {
			logger.error("Failed to get values for column: {}", columnNumber, e);
			throw new RuntimeException("Failed to retrieve column values", e);
		}
	}
	public void clickMagnifierGlass(String columnnumber,String searchterm) {
		page.locator("(//span[@class='dx-menu-item-popout-container'])[" + columnnumber + "]").click();
		page.locator("//span[text()='" + searchterm + "']").click();

	}
	public void searchTextBox(String columnnumber, String text) {
		Locator input = page.locator("(//input[@class='dx-texteditor-input'])[" + columnnumber + "]");
		input.click();
		input.fill(text);
	}

	public void searchInGrid(String lastName, String columnNumber, String searchTerm,
							 String textColumn, String text) {
		try {
			logger.info("Starting grid search operation");
			logger.debug("Parameters - LastName: {}, Column: {}, SearchTerm: {}, TextColumn: {}, Text: {}",
					lastName, columnNumber, searchTerm, textColumn, text);
			logger.info("Filling last name field with: {}", lastName);
			page.fill(SAMPLE, lastName);
			logger.debug("Last name field filled successfully");
			logger.info("Clicking search button");
			page.click(SAMPLE);
			logger.debug("Search button clicked successfully");
			page.waitForLoadState(LoadState.NETWORKIDLE);
			logger.debug("Search results loaded");
			logger.info("Clicking magnifier glass in column {} for term: {}", columnNumber, searchTerm);
			clickMagnifierGlass(columnNumber, searchTerm);
			logger.debug("Magnifier glass clicked successfully");
			logger.info("Entering text '{}' in column {}", text, textColumn);
			searchTextBox(textColumn, text);
			logger.debug("Text search completed successfully");
			logger.info("Grid search operation completed successfully");
			page.waitForTimeout(1000);
		} catch (Exception e) {
			logger.error("Grid search failed! Error: {}", e.getMessage());
			logger.debug("Stack trace:", e);
			throw new RuntimeException("Grid search operation failed", e);
		}
	}
	
	public boolean checkAndExpand(Page page, String locator, String sectionName) {
		logger.info("Checking if " + sectionName + " section is expanded or collapsed.");

		// Check if the section is expanded or collapsed
		boolean isExpanded = page.isVisible(locator + "/following-sibling::div[contains(@class, 'expanded')]");
		Assert.assertFalse(isExpanded, sectionName + " section should be collapsed initially.");

		if (!isExpanded) {
			logger.info(sectionName + " section is collapsed. Expanding now.");
			page.click(locator);

			// Verify if the section is expanded
			isExpanded = page.isVisible(locator + "/following-sibling::div[contains(@class, 'expanded')]");
			if (isExpanded) {
				logger.info(sectionName + " section is successfully expanded.");
			} else {
				logger.error("Failed to expand " + sectionName + " section.");
			}
		}
		return isExpanded;
	}
}
	

		



