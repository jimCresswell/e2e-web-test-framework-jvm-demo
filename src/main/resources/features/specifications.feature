Feature: Using the booking website

  Scenario Outline: Booking works
    Given I want to use the booking website
    When I try to create a <booking type> booking
    Then that booking is <accepted or not>

    Examples: valid bookings
      | booking type | accepted or not |
      | valid        | accepted        |
      | invalid      | not accepted    |


  @pending
  Scenario: Bookings can be deleted

  @pending
  Scenario: Existing bookings are displayed on page load