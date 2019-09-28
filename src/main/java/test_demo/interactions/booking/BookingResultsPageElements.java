package test_demo.interactions.booking;

import org.openqa.selenium.By;

public class BookingResultsPageElements {
    static By BOOKINGS_LIST = By.cssSelector("#bookings [id]");

    // Need xpath for relative selections.
    // These are the divs containing the data for a single booking.
    static By BOOKING_DATA_ELS = By.xpath("./div");

    static public By getBookingByNameSelector(String name) {
        return By.xpath("//*[@id=\"bookings\"]//p[contains(text(), \"" + name + "\")]");
    }
}
