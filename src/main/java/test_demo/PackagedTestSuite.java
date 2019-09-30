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
)
public class PackagedTestSuite {
}
