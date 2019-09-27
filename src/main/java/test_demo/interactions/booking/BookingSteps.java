package test_demo.interactions.booking;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import test_demo.data.Booking;

import java.util.List;

import static test_demo.interactions.booking.BookingResultsPageElements.*;
import static test_demo.interactions.booking.DataInputPageElements.*;

public class BookingSteps extends UIInteractionSteps {

    @Step("Try to create a booking with data {0}")
    public void tryToCreateBooking(Booking booking) {
        enter(booking.getFirstName()).into(FIRST_NAME);
        enter(booking.getLastName()).into(LAST_NAME);
        enter(booking.getTotalPrice()).into(PRICE);

        $(DEPOSIT).selectByVisibleText(booking.getDepositPaid());

        enter(booking.getCheckIn()).into(CHECK_IN);
        enter(booking.getCheckOut()).into(CHECK_OUT);

        $(SAVE_BUTTON).click();
    }

    public List<WebElementFacade> getBookings() {
        return $$(BOOKINGS_LIST);
    }
}
