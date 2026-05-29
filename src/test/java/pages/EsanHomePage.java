package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EsanHomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public EsanHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void ingresarAlPortal() {
        driver.get("https://www.esan.edu.pe/");
    }

    public String obtenerTituloDeLaPagina() {
        return driver.getTitle();
    }

    // MODIFICADO: En lugar de buscar el botón, cargamos directamente la versión en inglés
    public void cambiarAIngles() {
        driver.get("https://www.esan.edu.pe/en/");
    }

    public String obtenerUrlActual() {
        return driver.getCurrentUrl();
    }
}