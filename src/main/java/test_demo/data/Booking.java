package test_demo.data;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

import java.util.List;

import static java.lang.Boolean.getBoolean;

public class Booking {
    private int systemId;
    private String firstName;
    private String lastName;
    // totalPrice stored as String to support user-like bad input
    // Note the booking system also supports floating point notation e.g. `1.1e+99`
    private String totalPrice;
    private Boolean depositPaid;
    private String checkIn;
    private String checkOut;

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public int getTotalPriceInt() {
        return Integer.parseInt(totalPrice);
    }

    public String getDepositPaid() {
        return depositPaid.toString();
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    /**
     * With system booking ID.
     *
     * @param systemId
     * @param firstName
     * @param lastName
     * @param totalPrice
     * @param depositPaid
     * @param checkIn
     * @param checkOut
     */
    public Booking(int systemId, String firstName, String lastName, String totalPrice, Boolean depositPaid, String checkIn, String checkOut) {
        this.systemId = systemId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    /**
     * Without system booking ID, price as String.
     *
     * @param firstName
     * @param lastName
     * @param totalPrice
     * @param depositPaid
     * @param checkIn
     * @param checkOut
     */
    public Booking(String firstName, String lastName, String totalPrice, boolean depositPaid, String checkIn, String checkOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    /**
     * Without system booking ID, price as floating point number.
     *
     * @param firstName
     * @param lastName
     * @param totalPrice
     * @param depositPaid
     * @param checkIn
     * @param checkOut
     */
    public Booking(String firstName, String lastName, Double totalPrice, boolean depositPaid, String checkIn, String checkOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice.toString();
        this.depositPaid = depositPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    /**
     * Generate a JSON object from the booking data for POST interactions.
     *
     * @return a JSON representation of the booking data.
     */
    public JSONObject toPostJson() {
        JSONObject bookingDates = new JSONObject()
                .put("checkin", checkIn)
                .put("checkout", checkOut);

        JSONObject bookingJson = new JSONObject()
                .put("firstname", firstName)
                .put("lastname", lastName)
                .put("totalprice", totalPrice)
                .put("depositpaid", depositPaid)
                .put("bookingdates", bookingDates);

        return bookingJson;
    }

    public static Booking fromUIData(List<String> bookingDataList) {
        int _systemId = Integer.parseInt(bookingDataList.get(0));
        String _firstName = bookingDataList.get(1);
        String _lastName = bookingDataList.get(2);
        String _totalPrice = bookingDataList.get(3);
        Boolean _depositPaid = getBoolean(bookingDataList.get(4));
        String _checkIn = bookingDataList.get(5);
        String _checkOut = bookingDataList.get(6);

        return new Booking(_systemId, _firstName, _lastName, _totalPrice, _depositPaid, _checkIn, _checkOut);
    }

    public static Booking fromJsonResponse(int systemId, JsonPath data) {
        int _systemId = systemId;
        String _firstName = data.get("firstname");
        String _lastName = data.get("lastname");
        // The API response comes back with the price as an int.
        String _totalPrice = data.get("totalprice").toString();
        Boolean _depositPaid = data.get("depositpaid");
        String _checkIn = data.get("bookingdates.checkin");
        String _checkOut = data.get("bookingdates.checkout");

        return new Booking(_systemId, _firstName, _lastName, _totalPrice, _depositPaid, _checkIn, _checkOut);
    }
}
