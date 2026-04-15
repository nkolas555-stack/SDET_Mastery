import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        // 1. Configura el driver de Chrome automáticamente
        WebDriverManager.chromedriver().setup();

        // 2. Abre el navegador
        WebDriver driver = new ChromeDriver();

        // 3. Ve a tu LinkedIn para validar el éxito
        driver.get("https://linkedin.com");

        System.out.println("Page Title is: " + driver.getTitle());

        // 4. Cierra el navegador
        driver.quit();
    }
}
