package org.cardinality.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.cardinality.factory.BrowserFactory;
import org.cardinality.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.itextpdf.html2pdf.HtmlConverter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private final BrowserFactory browserFactory = new BrowserFactory();
    Properties properties = browserFactory.initializeConfigProperties();
    protected Page page;
    protected LoginPage homepage;
    private String browserName;
    protected Dotenv dotenv;
    protected Faker faker = new Faker();
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    @Parameters({ "browserName", "headless" })
    public void setUp(@Optional("chromium") String browserName, @Optional("false") String headless, ITestResult result)
            throws IllegalArgumentException {
        this.browserName = browserName;

        String projectDirectory = System.getProperty("user.dir");
        dotenv = Dotenv.configure().directory(projectDirectory).filename(".env").load();
        page = browserFactory.initializeBrowser(browserName, headless);
        int defaultTimeoutMs = Integer.parseInt(properties.getProperty("DEFAULT_TIMEOUT_MS", "60000"));
        page.setDefaultTimeout(defaultTimeoutMs);
        page.navigate(properties.getProperty("BASE_URL") + (properties.getProperty("RESOURCE_PATH")).trim());

        String testName = result.getMethod().getMethodName();
        logger.info("Test Case '{}' execution is started", testName);

        // Initialize Extent Reports
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                    Paths.get("target", "extent-report.html").toString());
            sparkReporter.config().setTheme(Theme.DARK);
            extent.attachReporter(sparkReporter);
        }
        test = extent.createTest(testName);

        page.context().tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
        homepage = new LoginPage(page);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        handleTestFailures(result);
        page.context().tracing()
                .stop(new Tracing.StopOptions().setPath(Paths.get("target", "trace-" + result.getName() + ".zip")));
        try {
            if (page != null && page.context() != null && page.context().browser() != null) {
                page.context().browser().close();
            }
        } catch (Exception e) {
            logger.error("Failed to close browser: {}", e.getMessage(), e);
        }

        // Log result to Extent Reports
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
            try {
                Path screenshotPath = captureScreenshot(result.getName());
                test.addScreenCaptureFromPath(screenshotPath.toString());
            } catch (IOException e) {
                logger.error("Failed to attach screenshot to Extent report", e);
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else {
            test.skip("Test skipped");
        }
        extent.flush();

        // Convert HTML report to PDF
        // convertHtmlToPdf("extent-report.html", "extent-report.pdf");
    }

    protected void handleTestFailures(ITestResult result) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = formatter.format(currentDateTime);

        String resultName = result.getName() + "_" + browserName + "_" + formattedDateTime;
        Path screenshotDir = Paths.get("./target/screenshots/");
        try {
            Files.createDirectories(screenshotDir);
        } catch (IOException e) {
            logger.error("Failed to create screenshots directory", e);
        }

        Path screenshotPath = screenshotDir.resolve(resultName + ".png");

        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("TEST FAILED: " + result.getName());
            try {
                page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(true));
                attachScreenshotToAllureReport(resultName, screenshotPath);
            } catch (Exception e) {
                logger.error("Issue taking screenshot or attaching to Allure report", e);
            }
        } else {
            logger.info("TEST PASSED: " + result.getName());
        }
    }

    @Attachment(value = "{resultName}", type = "image/png")
    private byte[] attachScreenshotToAllureReport(String resultName, Path screenshotPath) throws IOException {
        byte[] screenshotBytes = Files.readAllBytes(screenshotPath);
        Allure.addAttachment(resultName, new ByteArrayInputStream(screenshotBytes));
        return screenshotBytes;
    }

    private Path captureScreenshot(String testName) throws IOException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = formatter.format(currentDateTime);

        String resultName = testName + "_" + browserName + "_" + formattedDateTime;
        Path screenshotDir = Paths.get("./target/screenshots/");
        Files.createDirectories(screenshotDir);

        Path screenshotPath = screenshotDir.resolve(resultName + ".png");
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(true));
        return screenshotPath;
    }

    public void convertHtmlToPdf(String htmlFilePath, String pdfFilePath) {
        try (FileOutputStream outputStream = new FileOutputStream(pdfFilePath)) {
            HtmlConverter.convertToPdf(new FileInputStream(htmlFilePath), outputStream);
            System.out.println("Converted HTML report to PDF: " + pdfFilePath);
        } catch (IOException e) {
            System.err.println("Failed to convert HTML report to PDF");
            e.printStackTrace();
        }
    }
}
