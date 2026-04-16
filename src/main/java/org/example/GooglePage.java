package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage {
    WebDriver driver;

    // Localizador: El "id" de la barra de búsqueda
    By searchBox = By.name("q");

    // Constructor: Para que la página use el navegador que le mandemos
    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    // Acción: Escribir y buscar
    public void search(String text) {
        driver.findElement(searchBox).sendKeys(text + Keys.ENTER);
    }
}
