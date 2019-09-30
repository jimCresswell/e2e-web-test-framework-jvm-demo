# End-to-end Test Demo
Example testing of a third-party website with end-to-end tests through the UI and network API, using the JVM based [Serenity-BDD](http://www.thucydides.info/#/) framework with Cucumber, [WebDriver](https://www.seleniumhq.org/docs/03_webdriver.jsp) and [REST Assured](http://rest-assured.io/), and built with [Gradle](https://gradle.org/) (Groovy DSL).

The tests are driven from user-experience level specifications contained in ["feature files"](src/main/resources/features/) using [Cucumber](https://cucumber.io/)'s [Gherkin](https://cucumber.io/docs/gherkin/reference/) syntax.

## Project Structure
Because these tests are intended to be a standalone application running against a third party website the "test" code lives in the `main` module rather than in `test`.

### The Specifications

The scenarios driving the tests are in the [features directory](src/main/resources/features/). The examples are divided into
  * Specifications checked [through the UI](features/user_experience/booking.feature) of the service under test.
  * Specifications checked [through the REST API](features/network_behaviour/booking_api.feature) of the service under test.
  * Scenarios describing [known regressions or bugs](features/regressions/regressions.feature). These should be regularly reviewed to decide if the issues they describe are still a risk or if they should be deleted or moved to a specification.

## Use

### System Requirements
The UI tests require the Chrome browser to be installed on the system. The packaged ChromeDriver binaries will only operate against Chrome 77, you can [specify your own ChromeDriver](#starting-the-application).

There is nothing Chrome specific about any of the tests. If you want to run against other browsers set the [appropriate environment variables](http://www.thucydides.info/docs/serenity/#_running_serenity_tests_from_the_command_line) e.g. `webdriver.driver=firefox`, or edit the [serenity.conf](serenity.conf) file.

### As Part of the Check Lifecycle Task
`gradlew check` will run the checks and generate a Serenity test report at `target/site/serenity/index.html`.

### Packaging as an Application
To package the application use `gradlew assembleDist`.

### Using as a Packaged Application

#### Starting the Application
To use the packaged application, unzip it (`.zip` or `.tar` according to preference), then use the platform specific start scripts.

  * Windows `test-demo.bat`
  * Linux/mac `test-demo`

The application relies on a platform appropriate ChromeDriver binary being available and specified with the `webdriver.driver.chrome="path/to/chromedriver"` environment variable.
ChromeDrivers for Chrome 77 are packaged with the distribution and [launch scripts](src/main/launchers) are provided in the package `bin` directory which set the appropriate environment variable and start the tests.

  * Windows `windows_launcher.bat`
  * Linux `linux_launcher.sh`
  * Mac `mac_launcher.sh`

To specify your own ChromeDriver (e.g. if you need a different Chrome/ChromeDriver combination) set the `webdriver.driver.chrome="path/to/chromedriver"` environment variable.

#### The Test Report
The Serenity test report is generated in the unzipped application folder at `serenity_report/index.html`.

## Note on WebDriver Binaries in Version Control and Alternatives
I've included the binaries for ChromeDriver 77 [src\test\resources\webdriver]. I wouldn't normally put binaries in Git but in my experience this saves a lot of time, especially with teams of mixed experience levels. These binaries will only work with Chrome 77 and will be out of date quickly, they can be replaced with up to date versions at the [Chromdriver site](https://sites.google.com/a/chromium.org/chromedriver/downloads).

For decoupling and scalability it is better to operate the tests against a [Selenium Grid](https://github.com/SeleniumHQ/selenium/wiki/Grid2) set up. Container-based solutions e.g. [Zalenium](https://opensource.zalando.com/zalenium/) are available. 
