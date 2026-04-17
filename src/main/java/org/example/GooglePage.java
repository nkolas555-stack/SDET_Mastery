package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing the search page (e.g., Wikipedia).
 * Encapsulates private locators and page-specific action methods.
 */
public class GooglePage extends BasePage { // "extends" es la clave

    private By searchBox = By.id("searchInput");
    private By firstHeading = By.id("firstHeading");

    /**
     * Provides secure access to the main heading locator.
     * @return By locator for the first heading.
     */
    public By getFirstHeadingLocator() {
        return firstHeading;
    }

    public GooglePage(WebDriver driver) {
        super(driver); // Llama al constructor del padre (BasePage)
    }

    /**
     * Executes an automated search query.
     * @param text The search term to be entered.
     */
    public void search(String text) {
        type(searchBox, text + Keys.ENTER); // Usamos el método del padre
    }

    /**
     * Retrieves the text from the main page heading.
     * @return String containing the heading text.
     */
    public String getTitleText() {
        return getText(firstHeading); // Usamos el método del padre
    }
}
