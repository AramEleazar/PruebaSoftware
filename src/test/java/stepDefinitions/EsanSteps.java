package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import hooks.Hooks;
import org.junit.Assert;
import pages.EsanHomePage;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EsanSteps {

    private EsanHomePage esanPage;
    private String tituloObtenido;
    private Scenario scenario;

    @Before
    public void asignarEscenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("que el usuario navega al portal web de ESAN")
    public void usuarioNavegaAESAN() {
        esanPage = new EsanHomePage(Hooks.getDriver());
        esanPage.ingresarAlPortal();
    }

    // =========================================================================
    // PASOS PARA EL PRIMER CASO DE PRUEBA (TÍTULO DE LA PÁGINA)
    // =========================================================================

    @When("la página web carga por completo")
    public void paginaCargaPorCompleto() {
        tituloObtenido = esanPage.obtenerTituloDeLaPagina();
        Hooks.tomarCapturaPaso(this.scenario, "Pagina_Cargada_ESAN");
    }

    @Then("el título de la pestaña debe ser {string}")
    public void verificarTituloPestaña(String tituloEsperado) {
        Assert.assertTrue(
                "El título del portal de ESAN no coincide. Título actual: " + tituloObtenido,
                tituloObtenido.contains(tituloEsperado)
        );
    }

    // =========================================================================
    // PASOS PARA EL SEGUNDO CASO DE PRUEBA (CAMBIAR IDIOMA)
    // =========================================================================

    @When("el usuario hace clic en el botón de cambiar idioma")
    public void usuarioHaceClicEnElBotonDeCambiarIdioma() {
        esanPage.cambiarAIngles();
    }

    @Then("la página debe cargar la versión en inglés del portal")
    public void laPaginaDebeCargarLaVersionEnInglesDelPortal() {
        WebDriverWait espera = new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
        // Esperamos que la URL contenga "/en/" que confirma el cambio de idioma
        espera.until(ExpectedConditions.urlContains("/en/"));

        String urlActual = esanPage.obtenerUrlActual().toLowerCase();

        Assert.assertTrue(
                "No se cambió al idioma inglés. URL actual: " + urlActual,
                urlActual.contains("/en/")
        );

        // Saca la foto final mostrando la página en inglés para el reporte
        Hooks.tomarCapturaPaso(this.scenario, "Portal_En_Ingles");
    }
}