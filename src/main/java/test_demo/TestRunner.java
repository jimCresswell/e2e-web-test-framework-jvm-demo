package test_demo;

import net.thucydides.core.reports.html.HtmlAggregateStoryReporter;
import org.apache.commons.io.FileUtils;
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
        JUnitCore.runClasses(PackagedTestSuite.class);

        // Set the input and output directories for the test report.
        File sourceDirectory = new File("target/site/serenity/");
        File outputDirectory = new File("../serenity_report");

        // Make sure the output directory exists in an empty state.
        if (outputDirectory.exists()) {
            FileUtils.deleteDirectory(outputDirectory);
        }
        outputDirectory.mkdirs();

        // Generate the aggregated test report.
        HtmlAggregateStoryReporter reporter = new HtmlAggregateStoryReporter("Hotel Booking Test Demo");
        reporter.setOutputDirectory(outputDirectory);
        reporter.generateReportsForTestResultsFrom(sourceDirectory);

        // TO DO: consider automatically opening the test report in a browser?
    }
}
