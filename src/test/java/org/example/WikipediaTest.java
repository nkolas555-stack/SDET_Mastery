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
    public void validateWikipediaSearch(String searchTerm) {
        // we evaluate every term provided
        test = report.createTest("Validate Search: " + searchTerm);

        wiki.search(searchTerm);
        String title = wiki.getTitleText();

        test.info("Executed search for: " + searchTerm);

        // The title should not be empty
        Assert.assertFalse(title.isEmpty(), "The title should not be empty for: " + searchTerm);

        test.pass("Search successful for: " + searchTerm + ". Result title: " + title);
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
