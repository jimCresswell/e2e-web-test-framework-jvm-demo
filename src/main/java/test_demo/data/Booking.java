package test_demo.data;

import java.util.List;

import static java.lang.Boolean.getBoolean;

public class Booking {
    private String systemId;
    private String firstName;
    private String lastName;
    private String totalPrice;
    private Boolean depositPaid;
    private String checkIn;
    private String checkOut;

    public String getSystemId() {
        return systemId;
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

    public String getDepositPaid() {
        return depositPaid.toString();
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public Booking(String systemId, String firstName, String lastName, String totalPrice, Boolean depositPaid, String checkIn, String checkOut) {
        this.systemId = systemId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Booking(String firstName, String lastName, String totalPrice, Boolean depositPaid, String checkIn, String checkOut) {
        this.systemId = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public static Booking bookingFromUIData(List<String> bookingDataList) {
        String _systemId = bookingDataList.get(0);
        String _firstName = bookingDataList.get(1);
        String _lastName = bookingDataList.get(2);
        String _totalPrice = bookingDataList.get(3);
        Boolean _depositPaid = getBoolean(bookingDataList.get(4));
        String _checkIn = bookingDataList.get(5);
        String _checkOut = bookingDataList.get(6);

        return new Booking(_systemId, _firstName, _lastName, _totalPrice, _depositPaid, _checkIn, _checkOut);
    }
}
