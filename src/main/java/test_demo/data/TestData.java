package test_demo.data;

import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Test data generation.
 */
public class TestData {

    public static final String VALID_BOOKING = "valid";
    public static final String INVALID_BOOKING = "invalid";
    public static final String OUT_BEFORE_IN_BOOKING = "outBeforeIn";

    /**
     * Get test data based on a descriptor string e.g. "valid" or "invalid"
     * @param bookingType the booking type description.
     * @return a booking data object.
     * @throws IllegalArgumentException only valid data descriptions are supported.
     */
    static public Booking getBooking(@NotNull String bookingType) throws IllegalArgumentException {
        switch (bookingType) {
            case VALID_BOOKING: return getValidBooking();
            case INVALID_BOOKING: return getInvalidBooking();
            case OUT_BEFORE_IN_BOOKING: return getOutBeforeInBooking();
            default: throw new IllegalArgumentException("Unsupported booking type: " + bookingType);
        }
    }

    /**
     * Generate a valid booking with a random first name.
     *
     * TO DO: consider randomising all variables to improve relative
     * test coverage and avoid passing by accident. Be aware this
     * makes repeatable testing harder.
     *
     * @return booking data.
     */
    @NotNull
    private static Booking getValidBooking() {
        return new Booking("valid_" + generateRandomName(), "last name", 100.00, true, "2021-01-20", "2021-01-21");
    }

    /**
     * Generate an invalid booking with a random first name.
     * @return booking data.
     */
    @NotNull
    private static Booking getInvalidBooking() {
        return new Booking("invalid_" + generateRandomName(), "last name", "AAA", true, "2021-01-20", "2021-01-21");
    }

    /**
     * Generate a booking with the check-out date before the check-in date.
     * @return booking data.
     */
    @NotNull
    private static Booking getOutBeforeInBooking() {
        return new Booking("John " + generateRandomName(), "Smith", 100.00, true, "2021-01-20", "1970-01-01");
    }

    /**
     * create a *unique* first name.
     *
     * First names won't work is identifiers in general because they
     * won't be unique. The GUI does contain a unique booking ID but
     * this isn't visible to users.
     *
     * @return a random alphabetic "name"
     */
    @NotNull
    private static String generateRandomName() {
        int minLength = 6;
        int maxLength = 10;
        return RandomStringUtils.randomAlphabetic(minLength, maxLength + 1);
    }
}
