package org.cardinality.common;

import org.cardinality.pages.ApplicantInfoPage;
import org.cardinality.pages.GridPage;
import org.cardinality.pages.ApplicantInfoPage;
import org.cardinality.pages.NotesPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Common {
    private NotesPage BSITFSearchPage;
    private ApplicantInfoPage sideMenuComponentPage;
    private GridPage gridPage;
    private static final Logger logger = LoggerFactory.getLogger(Common.class);

    public Common(NotesPage BSITFSearchPage, ApplicantInfoPage sideMenuComponentPage, GridPage gridPage) {
        this.BSITFSearchPage = BSITFSearchPage;
        this.gridPage = gridPage;
        this.sideMenuComponentPage = sideMenuComponentPage;
        logger.info("Common class initialized with page objects.");
    }

    private void navigateToNotebookMenu(String BSITFNumber, String menuName) {
        logger.info("Navigating to notebook menu: {}", menuName);
        logger.debug("Entering BSITF number: {}", BSITFNumber);
        logger.info("Entered BSITF number: {}", BSITFNumber);
        logger.debug("Clicking on search button.");
        logger.debug("Clicking on menu: {}", menuName);
        sideMenuComponentPage.clickMenu(menuName);
        logger.info("Clicked Menu Name: {}", menuName);
    }

    // Opening the menu
    public void clickMenuNameInBSITFNotebook(String BSITFNumber, String menuName) {
        logger.info("Opening menu: {} for BSITF BSITF: {}", menuName, BSITFNumber);
        navigateToNotebookMenu(BSITFNumber, menuName);
        logger.info("Opened menu: {} for BSITF BSITF: {}", menuName, BSITFNumber);
    }

    // Opens menu and click Add
    public void clickAddButton(String BSITFNumber, String menuName, String addRecord) {
        logger.info("Starting process to add record: {}", addRecord);
        navigateToNotebookMenu(BSITFNumber, menuName);
        logger.debug("Clicking on Add button for record: {}", addRecord);
        gridPage.clickAdd(addRecord);
        logger.info("Landed on Form page to add: {}", addRecord);
    }

    // Opens menu and clicks Edit
    public void clickEditButton(String BSITFNumber, String menuName, String editRecord) {
        logger.info("Starting process to edit record: {}", editRecord);
        navigateToNotebookMenu(BSITFNumber, menuName);
        logger.debug("Clicking on Edit button for record: {}", editRecord);
        gridPage.clickEdit(editRecord);
        logger.info("Landed on Form page to edit: {}", editRecord);
    }

    // Opens menu and delete a record
    public boolean deleteRecordInGrid(String BSITFNumber, String menuName, String deleteRecord) {
        logger.info("Starting process to delete record: {}", deleteRecord);
        navigateToNotebookMenu(BSITFNumber, menuName);
        logger.debug("Clicking on Delete button for record: {}", deleteRecord);
        gridPage.deleteRecordInGrid(deleteRecord);
        logger.debug("Confirming deletion of record: {}", deleteRecord);
        logger.info("Deleted record: {}", deleteRecord);
        // Assuming gridPage.deleteRecordInGrid returns a boolean indicating success
        return true; // or get actual result from gridPage
    }

    // Search in Grid
    public List<String> performSearchInGrid(String BSITFNumber, String menu, String magnifierGlass,
            String searchTerm, String searchBox, String searchData) {
        logger.info("Starting grid search for BSITF Number: {}", BSITFNumber);
        navigateToNotebookMenu(BSITFNumber, menu);
        logger.debug("Performing search with searchTerm: {}, field: {}, searchData: {}", searchTerm, searchBox,
                searchData);
        gridPage.search(magnifierGlass, searchTerm, searchBox, searchData);
        logger.info("Searched in grid with searchTerm: {}, field: {}, searchData: {}", searchTerm, searchBox,
                searchData);
        logger.debug("Retrieving search results for field: {}", searchBox);
        List<String> results = gridPage.getSearchResults(searchBox);
        logger.info("Search results: {}", results);
        return results;
    }
}