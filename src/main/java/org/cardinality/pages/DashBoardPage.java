package org.cardinality.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashBoardPage {

    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(DashBoardPage.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;

    public DashBoardPage(Page page) {

        this.page = page;

    }



}