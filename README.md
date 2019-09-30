# End-to-end Test Demo
Example testing of a third-party website with end-to-end tests through the UI and network API, using the JVM based [Serenity-BDD](http://www.thucydides.info/#/) framework with Cucumber, [WebDriver](https://www.seleniumhq.org/docs/03_webdriver.jsp) and [REST Assured](http://rest-assured.io/), and built with [Gradle](https://gradle.org/) (Groovy DSL).

The tests are driven from user-experience level specifications contained in ["feature files"](src/main/resources/features/) using [Cucumber](https://cucumber.io/)'s [Gherkin](https://cucumber.io/docs/gherkin/reference/) syntax.

## Project Structure
Because these tests are intended to be a standalone application running against a third party website the "test" code lives in the `main` module rather than in `test`.

### The Specifications

The scenarios driving the tests are in the [features directory](src/main/resources/features/). The examples are divided into
  * Specifications checked [through the UI](src/main/resources/features/user_experience/booking.feature) of the service under test.
  * Specifications checked [through the REST API](src/main/resources/features/network_behaviour/booking_api.feature) of the service under test.
  * Scenarios describing [known regressions or bugs](src/main/resources/features/regressions/regressions.feature). These should be regularly reviewed to decide if the issues they describe are still a risk or if they should be deleted or moved to a specification.
  
### Notes on test reliability.
UI tests with WebDriver are flaky, they throw up false negative test failures frequently, due to WebDriver itself, network failures, browser rendering quirks, and the interactions of multiple asynchronous systems. Even with the small number of UI scenarios in this example test suite it is not completely reliable. This is one of the reasons that end-to-end tests through the UI should be kept to the minimum acceptable number, testing key user journeys only. More complete testing of the system and UI can pushed down to API contract tests, integration tests and UI component tests with mocked data and services.

Automatically retrying failed tests is a common strategy, but it should only be done with care and discussion. What does it mean if a test passes one out of three times regularly? Clearly there is an underlying problem that could be in the code, the infrastructure, the test environment or elsewhere. Where appropriate the best option is often to push the validation to a lower level of testing, e.g. a single micro-service with mocked external services, or even an integration test.    

## Use

### System Requirements
The UI tests require the Chrome browser to be installed on the system. The packaged ChromeDriver binaries will only operate against Chrome 77, you can [specify your own ChromeDriver](#starting-the-application).

There is nothing Chrome specific about any of the tests. If you want to run against other browsers set the [appropriate environment variables](http://www.thucydides.info/docs/serenity/#_running_serenity_tests_from_the_command_line) e.g. `webdriver.driver=firefox`, or edit the [serenity.conf](serenity.conf) file.

### As Part of the Check Lifecycle Task
`gradlew check` will run the checks and generate a Serenity test report.

#### Test Results

If the tests are run from Gradle two test reports are generated automatically, a Serenity report `target/site/serenity/index.html` and a Gradle report `build/reports/tests/test/index.html`.

The test results in `xml` format (e.g. for integration with CI pipelines) are written to `build/test-results`. They aren't particularly detailed because the Junit XML format [doesn't support](https://github.com/serenity-bdd/serenity-core/issues/1729) the level of nested data required to make sense of Cucumber scenarios. However, these tests are intended to be very high-level only (great for finding user facing problems but not causes), detailed system checks should be happening at lower levels of testing (e.g. data-driven testing using Junit directly) and probably not end-to-end through the UI. 

### Packaging as an Application
To package the application use `gradlew assembleDist`.

### Using as a Packaged Application

#### Starting the Application
To use the packaged application, unzip it (`.zip` or `.tar` according to preference), then use the platform specific start scripts.

  * Windows `test-demo.bat`
  * Linux/mac `test-demo`

The application relies on a platform appropriate ChromeDriver binary being available and specified with the `webdriver.driver.chrome="path/to/chromedriver"` environment variable.
ChromeDrivers for Chrome 77 are packaged with the distribution in the `lib/webdriver` directory and [launch scripts](src/main/launchers) are provided in the package `bin` directory which set the appropriate environment variable and start the tests.

  * Windows `windows_launcher.bat`
  * Linux `linux_launcher.sh`
  * Mac `mac_launcher.sh`

To specify your own ChromeDriver (e.g. if you need a different Chrome/ChromeDriver combination) set the `webdriver.driver.chrome="path/to/chromedriver"` environment variable.

Note: the linux and mac launch scripts haven't been tested yet, if they are not working please raise an issue and in the meantime set the `webdriver.driver.chrome` environment variable manually.

#### The Packaged Application Test Report
The Serenity test report is generated in the unzipped application folder at `serenity_report/index.html`.

## Note on WebDriver Binaries in Version Control and Alternatives
I've included the binaries for ChromeDriver 77 [src\test\resources\webdriver]. I wouldn't normally put binaries in Git but in my experience this saves a lot of time, especially with teams of mixed experience levels. These binaries will only work with Chrome 77 and will be out of date quickly, they can be replaced with up to date versions at the [Chromdriver site](https://sites.google.com/a/chromium.org/chromedriver/downloads).

For decoupling and scalability it is better to operate the tests against a [Selenium Grid](https://github.com/SeleniumHQ/selenium/wiki/Grid2) set up. Container-based solutions e.g. [Zalenium](https://opensource.zalando.com/zalenium/) are available. 

## Known Issues
There are scenarios marked as `@pending` intended as examples of potential further tests to implement. Normally these scenarios are categorised as pending by the runner and included in the test report as pending tests. Currently in some circumstances Serenity-Cucumber attempts to run the unimplemented `@pending` tests and throws exceptions. For the time being `@pending` scenarios have been explicitly excluded from the test suites.