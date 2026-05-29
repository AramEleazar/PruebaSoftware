package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    private static WebDriver driver;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        // Leer propiedad enviada desde consola
        // Por defecto será "false"
        String ejecucionHeadless = System.getProperty("headless", "false");

        // Configuración de opciones Chrome
        ChromeOptions options = new ChromeOptions();

        if (ejecucionHeadless.equalsIgnoreCase("true")) {

            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        // Inicializar driver
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {

        // Cerrar navegador automáticamente
        if (driver != null) {
            driver.quit();
        }
    }

    // Método estático para obtener driver
    public static WebDriver getDriver() {
        return driver;
    }

    // Método para capturar screenshots
    public static void tomarCapturaPaso(Scenario escenario, String nombrePaso) {

        if (driver != null) {

            try {

                final byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);

                escenario.attach(
                        screenshot,
                        "image/png",
                        "Paso - " + nombrePaso
                );

            } catch (Exception e) {

                System.err.println(
                        "Error al generar captura: "
                                + e.getMessage()
                );
            }
        }
    }
}