package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Usamos el Page Object Model
        GooglePage google = new GooglePage(driver);

        driver.get("https://google.com");
        google.search("Mejor sueldo QA Automation México 2026");

        System.out.println("Búsqueda realizada con POM.");
        driver.quit();
    }
}
