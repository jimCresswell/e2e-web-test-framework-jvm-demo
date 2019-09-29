package test_demo.interactions.ui_navigation;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.webdriver.DriverConfigurationError;

/**
 *  This is the "lean page object" approach.
 *  Page objects contain mostly URLs and identifiers, and actions are delegated to the Serenity steps.
 *
 * The default URL is overridden according to the `environments` values in `serenity.conf`.
 */
@DefaultUrl("http://hotel-test.equalexperts.io/")
public class BookingHomePage extends PageObject {

    // Deal with Chromedriver flakiness by trying twice.
    public void safeOpen() {
        try{
            super.open();
        } catch(DriverConfigurationError e) {
            super.open();
        }
    }
}
