package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        GooglePage wiki = new GooglePage(driver);

        driver.get("https://wikipedia.org");

        wiki.search("Software Testing");

        wait.until(ExpectedConditions.visibilityOfElementLocated(wiki.getFirstHeadingLocator()));

        String title = wiki.getTitleText();
        System.out.println("Título capturado: " + title);

        if(title.contains("Pruebas de software")) {
            System.out.println("TEST PASSED: El título es correcto.");
        } else {
            System.out.println("TEST FAILED: El título no coincide.");
        }

        driver.quit();
    }
}
