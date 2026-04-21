package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WikipediaTest {
    WebDriver driver;
    GooglePage wiki;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wiki = new GooglePage(driver);
        driver.get("https://wikipedia.org");
    }

    @Test
    public void validateWikipediaSearch() throws InterruptedException {
        wiki.search("Software Testing");
        //Thread.sleep(3000);
        String title = wiki.getTitleText();
        // Esto es un Assertion de verdad:
        Assert.assertTrue(title.contains("Pruebas de software"), "El título no coincide");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
