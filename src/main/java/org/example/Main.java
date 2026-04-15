package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // 1. Ir a Google
        driver.get("https://www.google.com");

        // 2. Encontrar la barra de búsqueda (Se llama "q" por 'query')
        WebElement searchBox = driver.findElement(By.name("q"));

        // 3. Escribir algo y dar ENTER
        searchBox.sendKeys("Mejor sueldo QA Automation México 2026");
        searchBox.sendKeys(Keys.ENTER);

        // 4. Esperar 3 segundos para ver el resultado (Solo para que tú lo veas)
        Thread.sleep(3000);

        System.out.println("Búsqueda finalizada con éxito.");

        driver.quit();
    }
}
