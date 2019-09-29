package test_demo;

import net.thucydides.core.reports.html.HtmlAggregateStoryReporter;
import org.junit.runner.JUnitCore;

import java.io.File;
import java.io.IOException;

/**
 * This is the entry point when the tests are initiated as a
 * standalone Java application.
 */
public class TestRunner {
    public static void main(String[] args) throws IOException {

        // Run the main test suite.
        JUnitCore.runClasses(TestSuite.class);

        // Generate the aggregated test report.
        File sourceDirectory = new File("target/site/serenity/");
        HtmlAggregateStoryReporter reporter = new HtmlAggregateStoryReporter("Hotel Booking Test Demo");
        reporter.setOutputDirectory(sourceDirectory);
        reporter.generateReportsForTestResultsFrom(sourceDirectory);

        // TO DO: consider automatically opening the test report in a browser?
    }
}
