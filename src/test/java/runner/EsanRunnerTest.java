package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"stepDefinitions", "hooks"}, // Agregamos "hooks" al mapeo de ejecución
        plugin = {"pretty", "html:target/cucumber-esan-report.html"}
)
public class EsanRunnerTest {
}