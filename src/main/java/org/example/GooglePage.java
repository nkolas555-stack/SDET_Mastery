package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage { // Por ahora déjale el nombre para no romper nada
    WebDriver driver;
    By searchBox = By.id("searchInput"); // ID de la barra de Wikipedia
    By firstHeading = By.id("firstHeading"); // ID del título del resultado

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String text) {
        driver.findElement(searchBox).sendKeys(text + "\n");
    }

    public String getTitleText() {
        return driver.findElement(firstHeading).getText();
    }
}
