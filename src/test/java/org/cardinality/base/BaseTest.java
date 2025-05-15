package org.cardinality.base;

import com.microsoft.playwright.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected static Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true)); // <-- headless true!
        page = browser.newPage();
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
