package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

/**
 * BasePage class that centralizes Selenium WebDriver and WebDriverWait configuration.
 * Implements the Page Object Model (POM) design pattern by providing common
 * methods for web element interaction.
 *
 * @author Senior SDET - ESFM Engineer Lorenzo Nicolas de la Cruz
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor for the BasePage.
     * @param driver WebDriver instance for navigation.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Performs an explicit wait and types text into a specific locator.
     * @param locator The By locator of the element.
     * @param text The string to be typed.
     */
    protected void type(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    /**
     * Waits for element visibility and retrieves its text.
     * @param locator The By locator of the element.
     * @return The visible text of the element.
     */
    protected String getText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public String takeScreenshot(String screenshotName) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Relative path
        String relativePath = "screenshots/" + screenshotName + ".png";
        // Absolute path
        String absolutePath = System.getProperty("user.dir") + "/target/" + relativePath;

        File finalDestination = new File(absolutePath);
        FileUtils.copyFile(source, finalDestination);

        return relativePath; // IMPORTANT: RETURN THE RELATIV PATH
    }

}
