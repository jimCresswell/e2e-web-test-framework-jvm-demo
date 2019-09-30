package test_demo.interactions.booking.ui;

import org.openqa.selenium.By;

public class DataInputPageElements {
    static By FIRST_NAME = By.cssSelector("#form #firstname");
    static By LAST_NAME = By.cssSelector("#form #lastname");
    static By PRICE = By.cssSelector("#form #totalprice");
    static By DEPOSIT = By.cssSelector("#form #depositpaid");
    static By CHECK_IN = By.cssSelector("#form #checkin");
    static By CHECK_OUT = By.cssSelector("#form #checkout");
    static By SAVE_BUTTON = By.cssSelector("#form input[value*=\"Save\"]");

    static By PAGE_HEADER = By.cssSelector(".jumbotron");
    static By DATE_PICKER = By.cssSelector("#ui-datepicker-div");
}

