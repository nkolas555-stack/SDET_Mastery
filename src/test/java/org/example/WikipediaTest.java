package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;

import java.io.IOException;

/**
 * Test suite for Wikipedia functionality.
 * Implements TestNG lifecycle and ExtentReports for visual reporting.
 */
public class WikipediaTest {
    WebDriver driver;
    GooglePage wiki;

    // Reporting infrastructure
    static ExtentReports report;
    static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        // Initialize the HTML reporter and define the output path
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Senior_Execution_Report.html");
        report = new ExtentReports();
        report.attachReporter(spark);
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wiki = new GooglePage(driver);
        driver.get("https://wikipedia.org");
    }

    @Test(dataProvider = "searchData")
    public void validateWikipediaSearch(String searchTerm) throws IOException {
        // we evaluate every term provided
        test = report.createTest("Validate Search: " + searchTerm);

        wiki.search(searchTerm);
        String title = wiki.getTitleText();

        // take tha picture:
        String screenshotPath = wiki.takeScreenshot(searchTerm); // Use the saved method
        test.pass("Search finished for: " + searchTerm).addScreenCaptureFromPath(screenshotPath); //put the picture in the report

        // The title should not be empty
        Assert.assertFalse(title.isEmpty(), "Title is empty");

    }

    @AfterMethod
    public void tearDown() {
        // Close the browser session after each test
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        // Consolidate all test results and generate the final HTML file
        report.flush();
    }

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        return new Object[][] {
                {"Software Testing"},
                {"Java Programming"},
                {"Selenium WebDriver"}
        };
    }

}
