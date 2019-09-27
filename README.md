# musical-octo-parakeet

Testing a third-party website with end-to-end tests through the UI, using the JVM based Serenity-BDD framework with Cucumber, Webdriver and Gradle (Groovy DSL).

The tests are driven from user-experience level specifications contained in ["feature files"](src/main/resources/features/search/search.feature) using [Cucumber](https://cucumber.io/)'s [Gherkin](https://cucumber.io/docs/gherkin/reference/) syntax.

## Project Structure

Because these tests are packaged as an executable JAR against a third party website the code lives in the `main` module rather than in `test`.

## Note on Webdriver Binaries

I've included the binaries for Chromedriver 77 [src\test\resources\webdriver]. I wouldn't normally put binaries in Git but in my experience this saves a lot of time, especially with teams of mixed experience levels. These binaries will only work with Chrome/Chromium 77 and will be out of date quickly, they can be replaced with up to date versions at the [Chromdriver site](https://sites.google.com/a/chromium.org/chromedriver/downloads).

For decoupling and scalability it is better to operate the tests against a [Selenium Grid](https://github.com/SeleniumHQ/selenium/wiki/Grid2) set up. [Container-based solutions](https://opensource.zalando.com/zalenium/) are available. 
