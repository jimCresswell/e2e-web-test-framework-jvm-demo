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
        // Try to create a booking through the UI with test data.
        Booking booking = bookingStepsUI.tryToCreateBooking(bookingType);

        /*
           Store the unique first name so it can later be used to verify UI contents.
           Using unique names because bookings IDs aren't visible in the UI and these
           tests are as far as possible simulating user experience.
         */
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
        // Try to delete a booking with a stored unique first name from an earlier step.
        bookingStepsUI.tryToDeleteBookingWithFirstName(bookingStepsUI.getIdentifyingFirstName());
    }

    /**
     * Assert that a valid booking is present or an invalid booking is not present.
     *
     * @param acceptedOrNot text description of whether the booking should be accepted by the system.
     */
    @Then("that booking is (.*)")
    public void that_booking_is_accepted_or_not(String acceptedOrNot) {
        // Determine if the booking should have been allowed or not.
        boolean bookingShouldExist = !acceptedOrNot.contains("not");

        // Get the stored unique first name from an earlier step.
        String identifyingFirstName = bookingStepsUI.getIdentifyingFirstName();

        if (bookingShouldExist) {
            // Assert that the named booking is in the UI.
            checkBookingExists(identifyingFirstName);
        } else {
            // Assert that the named booking is *not* in the UI.
            checkBookingFailsToAppear(identifyingFirstName);
        }
    }

    @Then("^that booking displays on page load$")
    public void thatBookingPersistsOnPageReload() {
        // Reload the page.
        navigateTo.bookingHomePage();

        // Assert that the named booking is in the UI on page load.
        checkBookingExists(bookingStepsUI.getIdentifyingFirstName());
    }

    @Then("^the booking is removed from the booking list$")
    public void the_booking_is_removed_from_the_booking_list() {
        // Assert a name booking is removed from the UI after being deleted via the UI.
        checkBookingIsRemoved(bookingStepsUI.getIdentifyingFirstName());
    }

    /**
     * Check that a booking made through the UI is displayed in the UI.
     * @param identifyingFirstName
     */
    private void checkBookingExists(String identifyingFirstName) {
        /*
         Strictly this step is sufficient to validate the scenario
         but asserting against a domain specific model is more
         maintainable and extensible.
        */
        bookingStepsUI.waitForFirstNamePresent(identifyingFirstName);

        // Get all the bookings displayed in the UI, parsed as Booking objects.
        List<Booking> bookingList = bookingStepsUI.getBookingsFromUI();

        /*
           Assert there is at least one booking and that there is
           a booking with the correct unique first name.
         */
        assertThat(bookingList)
                .matches(bookings -> bookings.size() > 0,
                        "There should be at least one bookings")
                .extracting(Booking::getFirstName)
                .contains(identifyingFirstName);
    }

    private void checkBookingFailsToAppear(String identifyingFirstName) {
        /*
           Wait for the named booking to *fail* to appear.
           This approach is an artifact of there being no UI feedback for invalid booking data.
         */
        bookingStepsUI.waitForFirstNameToFailToAppear(identifyingFirstName);

        // Assert the invalid booking is not present in the UI.
        checkBookingNotPresent(identifyingFirstName);
    }

    private void checkBookingIsRemoved(String identifyingFirstName) {
        // Wait for a deleted booking to disappear.
        bookingStepsUI.waitForFirstNameToDisappear(identifyingFirstName);

        // Assert the deleted booking is not in the UI.
        checkBookingNotPresent(identifyingFirstName);
    }

    /**
     * Assert that a named booking is not present in the UI.
     *
     * @param identifyingFirstName the unique booking name.
     */
    private void checkBookingNotPresent(String identifyingFirstName) {
        List<Booking> bookingList = bookingStepsUI.getBookingsFromUI();

        if (bookingList.size() > 0) {
            // Assert that a named booking is not in the UI.
            assertThat(bookingList)
                    .extracting(Booking::getFirstName)
                    .doesNotContain(identifyingFirstName);
        } else {
            // If the booking list is empty then the invalid/deleted booking isn't in the UI, job done.
            assertThat(bookingList).isEmpty();
        }
    }
}
