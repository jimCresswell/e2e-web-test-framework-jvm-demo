package test_demo.data;

public class TestData {

    private static Booking validBooking = new Booking("first name", "last name", "100", true, "2021-01-20", "2021-01-21");
    private static Booking invalidBooking = new Booking("first name", "last name", "AAA", true, "2021-01-20", "2021-01-21");
    private static Booking outBeforeInBooking = new Booking("first name", "last name", "100", true, "2021-01-20", "1970-01-01");

    static public Booking getBooking(String bookingType) {
        switch (bookingType) {
            case "valid": return validBooking;
            case "invalid": return invalidBooking;
            case "outBeforeIn": return outBeforeInBooking;
            default: throw new IllegalArgumentException("Unsupported booking type: " + bookingType);
        }
    }
}
