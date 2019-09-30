package test_demo;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/main/resources/features",
        // Explicitly exclude @pending tags to stop them running in the packaged version,
        // the runner is failing to mark them as pending then throwing an error when they
        // don't work. Seems to be a Serenity issue, possibly with Scenario Outlines
        tags = {"~@pending"}
)
public class TestSuite {
}
