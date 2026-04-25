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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;


/**
 * Test suite for Wikipedia functionality.
 * Implements TestNG lifecycle and ExtentReports for visual reporting.
 */
public class WikipediaTest {
    GooglePage wiki;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

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

    public WebDriver getDriver(){
        return driver.get();
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
        wiki = new GooglePage(getDriver());
        getDriver().get("https://wikipedia.org");
    }

    @Test(dataProvider = "excelData")//jsonDataReader
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
            getDriver().quit();
            driver.remove();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        // Consolidate all test results and generate the final HTML file
        report.flush();
    }

    @DataProvider(name = "excelData", parallel = true)
    public Object[][] getExcelData() throws IOException {
        String path = "src/test/resources/testData.xlsx";
        return ExcelReader.getTestData(path, "Sheet1");
    }

    @DataProvider(name = "jsonDataReader")
    public Object[][] getJsonData() throws Exception {
        // 1. Load JSON File
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/test/resources/testData.json");
        Object obj = parser.parse(reader);
        JSONArray dataList = (JSONArray) obj;

        // 2. Matrixx of TestNG
        Object[][] dataArray = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            JSONObject testCase = (JSONObject) dataList.get(i);
            dataArray[i][0] = testCase.get("searchTerm"); // "searchTerm" matches with JSON File
        }

        return dataArray;
    }
}