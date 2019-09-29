package test_demo.interactions.booking.ui;

import org.openqa.selenium.By;

public class BookingResultsPageElements {
    static By BOOKINGS_LIST = By.cssSelector("#bookings [id]");

    // Need xpath for relative selections.
    // These are the divs containing the data for a single booking.
    static By BOOKING_DATA_ELS = By.xpath("./div");

    static public By getBookingNameSelectorByFirstName(String firstName) {
        return By.xpath("//*[@id=\"bookings\"]//p[contains(text(), \"" + firstName + "\")]");
    }

    static public By getBookingDeleteSelectorByFirstName(String firstName) {
        return By.xpath("//*[@id=\"bookings\"]//p[contains(text(), \"" + firstName + "\")]/../..//input[@value=\"Delete\"]");
    }
}
