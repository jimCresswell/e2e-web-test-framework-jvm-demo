package test_demo.interactions.booking;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import test_demo.data.Booking;

public class BookingSteps extends UIInteractionSteps {

    @Step("Try to create a booking with data {0}")
    public void tryToCreateBooking(Booking booking) {
        enter(booking.getFirstName())
                .into(DataInputPageElements.FIRST_NAME);
        enter(booking.getLastName())
                .into(DataInputPageElements.LAST_NAME);
        enter(booking.getTotalPrice())
                .into(DataInputPageElements.PRICE);

        $(DataInputPageElements.DEPOSIT).selectByVisibleText(booking.getDepositPaid());

        enter(booking.getCheckIn())
                .into(DataInputPageElements.CHECK_IN);
        enter(booking.getCheckOut())
                .into(DataInputPageElements.CHECK_OUT);

        $(DataInputPageElements.SAVE_BUTTON).click();
    }
}
