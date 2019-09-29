package test_demo.interactions.booking.network;

import io.restassured.http.ContentType;

import io.restassured.response.Response;
import test_demo.data.Booking;
import test_demo.data.TestData;

import static net.serenitybdd.rest.RestRequests.when;
import static net.serenitybdd.rest.SerenityRest.given;

/**
 * Booking API interactions using Serenity-wrappedRestAssured.
 */
public class BookingStepsNetwork {

    private String identifyingFirstName = "";
    private int systemID;
    private Booking bookingResponse = null;

    // This data would be in a configuration class and dependent on environment, e.g local, staging, prod
    private String baseUrl = "http://hotel-test.equalexperts.io/";
    private String bookingPath = "booking";

    public String getIdentifyingFirstName() {
        return identifyingFirstName;
    }

    public void setIdentifyingFirstName(String identifyingFirstName) {
        this.identifyingFirstName = identifyingFirstName;
    }

    public int getSystemID() {
        return systemID;
    }

    public void setSystemID(int systemID) {
        this.systemID = systemID;
    }

    public Booking getBookingResponse() {
        return bookingResponse;
    }

    public void setBookingResponse(Booking bookingResponse) {
        this.bookingResponse = bookingResponse;
    }


    /**
     * Make the booking via the network API
     *
     * @param bookingType the booking type, e.g. valid.
     *
     * @return the booking test data used to make the booking,
     * including the returned booking ID.
     */
    public Booking tryToCreateBooking(String bookingType) {
        Booking booking = TestData.getBooking(bookingType);
        // Path /booking
        // Method POST
        // Request Content-Type: application/json; charset=utf-8
        // Response Content-Type: application/json; charset=utf-8
        Response response = given()
                .contentType(ContentType.JSON)
                .body(booking.toPostJson().toMap())
                .post(baseUrl + bookingPath);

        response.then().statusCode(200);

        int returnedSystemId = response
                .getBody()
                .jsonPath()
                .get("bookingid");

        booking.setSystemId(returnedSystemId);

        return booking;
    }

    /**
     * Get the booking from the network API
     *
     * @param systemID the system booking ID.
     *
     * @return the returned data encoded as a booking object.
     */
    public Booking getBooking(int systemID) {
        // Path /booking/{systemId}
        // Method GET
        // Request Content-Type: NA
        // Response Content-Type: application/json; charset=utf-8
        Response response = when()
                .get(baseUrl + bookingPath + "/{id}", systemID);

        response.then().statusCode(200);

        return Booking.fromJsonResponse(systemID, response.getBody().jsonPath());
    }

    /**
     * NOT IMPLEMENTED Delete the booking from the network API
     *
     * @param systemID the system booking ID.
     *
     * @return the request response payload.
     */
    public String deleteBooking(String systemID) {
        // Path /booking/{systemId}
        // Method DELETE
        // Auth Basic YWRtaW46cGFzc3dvcmQxMjM=
        // Request Content-Type: NA
        // Response Content-Type: text/plain; charset=utf-8
        // Status code: 201 Created
        return null;
    }
}
