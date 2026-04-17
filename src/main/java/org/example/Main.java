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

        // 1. Definimos la espera explícita (Hasta 10 segundos)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        GooglePage wiki = new GooglePage(driver);

        // 2. URL correcta de Wikipedia en español
        driver.get("https://wikipedia.org");

        // 3. Realizamos la búsqueda
        wiki.search("Software Testing");

        // 4. ESPERA SENIOR: Esperamos a que el título sea visible antes de leerlo
        wait.until(ExpectedConditions.visibilityOfElementLocated(wiki.getFirstHeadingLocator()));

        // 5. VALIDACIÓN
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
