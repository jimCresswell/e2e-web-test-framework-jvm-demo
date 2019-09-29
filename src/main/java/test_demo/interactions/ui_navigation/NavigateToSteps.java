package test_demo.interactions.ui_navigation;

import net.thucydides.core.annotations.Step;

public class NavigateToSteps {

    BookingHomePage bookingHomePage;

    @Step("Open the booking home page")
    public void bookingHomePage() {
        bookingHomePage.safeOpen();
    }
}
