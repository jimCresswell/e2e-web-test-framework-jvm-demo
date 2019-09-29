package test_demo.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import test_demo.data.Booking;
import test_demo.data.TestData;
import test_demo.interactions.booking.ui.BookingStepsUI;
import test_demo.interactions.ui_navigation.NavigateToSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * These are the Cucumber step definitions for UI interactions,
 * which call into the Serenity steps.
 */
public class StepDefinitionsUI {
    @Steps
    NavigateToSteps navigateTo;

    @Steps
    BookingStepsUI bookingStepsUI;

    @Given("I want to use the booking website")
    public void i_am_using_the_booking_site() {
        navigateTo.bookingHomePage();
    }

    @When("I try to create a (.*) booking")
    public void i_enter_booking_details(String bookingType) {
        Booking booking = bookingStepsUI.tryToCreateBooking(bookingType);
        bookingStepsUI.setIdentifyingFirstName(booking.getFirstName());
    }

    @When("^I try to create a booking with the check-out date before the check-in date$")
    public void i_try_to_leave_before_i_arrive() {
        i_enter_booking_details(TestData.OUT_BEFORE_IN_BOOKING);
    }

    @When("^there is a booking in the system$")
    public void there_is_A_booking_in_the_system() {
        i_enter_booking_details(TestData.VALID_BOOKING);
    }

    @When("^I delete that booking$")
    public void i_delete_that_booking() {
        bookingStepsUI.tryToDeleteBookingWithFirstName(bookingStepsUI.getIdentifyingFirstName());
    }

    /**
     * Assert that a valid booking is present or an invalid booking is not present.
     *
     * @param acceptedOrNot text description of whether the booking should be accepted by the system.
     */
    @Then("that booking is (.*)")
    public void that_booking_is_accepted_or_not(String acceptedOrNot) {
        boolean bookingShouldExist = !acceptedOrNot.contains("not");
        String identifyingFirstName = bookingStepsUI.getIdentifyingFirstName();

        if (bookingShouldExist) {
            checkBookingExists(identifyingFirstName);
        } else {
            checkBookingFailsToAppear(identifyingFirstName);
        }
    }

    @Then("^that booking displays on page load$")
    public void thatBookingPersistsOnPageReload() {
        navigateTo.bookingHomePage();
        checkBookingExists(bookingStepsUI.getIdentifyingFirstName());
    }

    @Then("^the booking is removed from the booking list$")
    public void the_booking_is_removed_from_the_booking_list() {
        checkBookingIsRemoved(bookingStepsUI.getIdentifyingFirstName());
    }

    private void checkBookingExists(String identifyingFirstName) {
        /*
         Strictly this step is sufficient to validate the scenario
         but asserting against a domain specific model is more
         maintainable and extensible.
        */
        bookingStepsUI.waitForFirstNamePresent(identifyingFirstName);

        List<Booking> bookingList = bookingStepsUI.getBookingsFromUI();
        assertThat(bookingList)
                .matches(bookings -> bookings.size() > 0,
                        "There should be at least one bookings")
                .extracting(Booking::getFirstName)
                .contains(identifyingFirstName);
    }

    private void checkBookingFailsToAppear(String identifyingFirstName) {
        bookingStepsUI.waitForFirstNameToFailToAppear(identifyingFirstName);
        checkBookingNotPresent(identifyingFirstName);
    }

    private void checkBookingIsRemoved(String identifyingFirstName) {
        bookingStepsUI.waitForFirstNameToDisappear(identifyingFirstName);
        checkBookingNotPresent(identifyingFirstName);
    }
    private void checkBookingNotPresent(String identifyingFirstName) {
        List<Booking> bookingList = bookingStepsUI.getBookingsFromUI();

        // If the booking list is empty then the invalid/deleted booking isn't in the UI, job done.
        if (bookingList.size() > 0) {
            assertThat(bookingList)
                    .extracting(Booking::getFirstName)
                    .doesNotContain(identifyingFirstName);
        } else {
            assertThat(bookingList).isEmpty();
        }
    }
}
