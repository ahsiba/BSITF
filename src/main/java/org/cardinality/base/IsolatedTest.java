package org.cardinality.base;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Base class for tests that require isolated browser instances.
 * Each test method will run in a fresh browser context that is closed afterward
 */

public class IsolatedTest extends BaseTest {

	/**
	 * Tears down the test envionment after each test method
	 * Handles test failure and closes the browser context
	 * 
	 * @throws IOException if an I/O error occurs
	 */


	@Attachment(type = "image/png")
	private void attachScreenshotToAllureReport(String resultName, Path screenshotPath) throws IOException {
		Allure.addAttachment(resultName, new ByteArrayInputStream(Files.readAllBytes(screenshotPath)));
	}
}
