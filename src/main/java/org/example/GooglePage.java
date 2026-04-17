package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GooglePage extends BasePage { // "extends" es la clave

    private By searchBox = By.id("searchInput");
    public By firstHeading = By.id("firstHeading");

    public GooglePage(WebDriver driver) {
        super(driver); // Llama al constructor del padre (BasePage)
    }

    public void search(String text) {
        type(searchBox, text + Keys.ENTER); // Usamos el método del padre
    }

    public String getTitleText() {
        return getText(firstHeading); // Usamos el método del padre
    }
}
