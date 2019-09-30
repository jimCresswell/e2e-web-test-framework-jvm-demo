package test_demo;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Test suite for the packaged version.
 *
 * The file structure is different for the packaged version.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "../lib/features",
        // Explicitly exclude @pending tags to stop them running in the packaged version,
        // the runner is failing to mark them as pending then throwing an error when they
        // don't work.
        // TO DO: Figure out why @pending tags are ignored in the packaged app.
        tags = {"~@pending"}
)
public class PackagedTestSuite {
}
