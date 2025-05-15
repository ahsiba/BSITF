package org.cardinality.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import org.cardinality.components.BaseComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BrowserFactory {
	private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
	private Playwright playwright;
    private Properties properties;
    private Browser browser;
    private BrowserContext context;
    private BrowserType browserType;

    public Page initializeBrowser(String browserName, String headless) throws IllegalArgumentException {
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chromium":
                browserType = playwright.chromium();
                break;
            case "chrome":
                browserType = playwright.chromium();
                break;
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "webkit":
                browserType = playwright.webkit();
                break;

            default:
                throw new IllegalArgumentException(
                        "Please provide a valid browser name (chrome, firefox, webkit or chromium).");
        }
        boolean isHeadless = headless.equalsIgnoreCase("true");
        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setChannel(browserName)
                .setHeadless(isHeadless));

        context = browser.newContext();

        return context.newPage();
    }

    public Properties initializeConfigProperties() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String configUrl = classLoader.getResource("config/config.properties").getPath();
            FileInputStream fileInputStream = new FileInputStream(configUrl);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
			logger.error("Failed to load configuration properties", e);

			throw new RuntimeException("Failed to load configuration properties", e);
        }

        return properties;
    }

    /**
     * +  * Closes all browser resources to prevent leaks
     * +
     */
    public void closeBrowser() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
