package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    /**
     * Performs an explicit wait and types text into a specific locator.
     * @param locator The By locator of the element.
     * @param text The string to be typed.
     */
    protected void type(By locator, String text) {
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator));
            org.openqa.selenium.WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator));
            org.openqa.selenium.WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
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
    public String getScreenshotBase64() {
        org.openqa.selenium.WebDriver baseDriver = driver;
        if (driver instanceof com.epam.healenium.SelfHealingDriver) {
            baseDriver = ((com.epam.healenium.SelfHealingDriver) driver).getDelegate();
        }
        return ((org.openqa.selenium.TakesScreenshot) baseDriver).getScreenshotAs(org.openqa.selenium.OutputType.BASE64);
    }

    public String takeScreenshot(String screenshotName) throws java.io.IOException {
        org.openqa.selenium.WebDriver baseDriver = driver;
        if (driver instanceof com.epam.healenium.SelfHealingDriver) {
            baseDriver = ((com.epam.healenium.SelfHealingDriver) driver).getDelegate();
        }
        java.io.File source = ((org.openqa.selenium.TakesScreenshot) baseDriver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);

        String relativePath = "screenshots/" + screenshotName + ".png";
        String absolutePath = System.getProperty("user.dir") + "/target/" + relativePath;

        java.io.File finalDestination = new java.io.File(absolutePath);
        org.apache.commons.io.FileUtils.copyFile(source, finalDestination);

        return relativePath;
    }



}
