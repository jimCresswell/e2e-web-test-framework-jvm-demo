package test_demo.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import test_demo.data.Booking;
import test_demo.data.TestData;
import test_demo.interactions.booking.network.BookingStepsNetwork;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The step definitions for network interactions.
 */
public class StepDefinitionsNetwork {
    @Steps
    BookingStepsNetwork bookingStepsNetwork;

    @Given("I make a valid booking")
    public void i_make_a_valid_booking() {
        // Try to create a booking with test data via a POST request.
        Booking booking = bookingStepsNetwork.tryToCreateBooking(TestData.VALID_BOOKING);

        // Store the unique first name generated for the test data for later comparison.
        bookingStepsNetwork.setIdentifyingFirstName(booking.getFirstName());

        // Store the system booking ID returned by the POST request.
        bookingStepsNetwork.setSystemID(booking.getSystemId());
    }

    @When("^I try to retrieve that booking$")
    public void iTryToRetrieveThatBooking() {
        // Get system booking ID returned by the POST request.
        int systemID = bookingStepsNetwork.getSystemID();

        // GET the booking data and parse it into a booking object.
        Booking bookingFromNetwork = bookingStepsNetwork.getBooking(systemID);

        // Store the GET response data.
        bookingStepsNetwork.setBookingResponse(bookingFromNetwork);
    }

    @Then("^the booking exists$")
    public void theBookingExists() {
        // Get the booking data returned by the GET request.
        Booking bookingFromNetwork = bookingStepsNetwork.getBookingResponse();

        /*
           Check that the identifying first name from the
           booking test data used in the POST request
           matches the first name returned by the GET request.
         */
        assertThat(bookingFromNetwork.getFirstName())
                .matches(bookingStepsNetwork.getIdentifyingFirstName());
    }
}
