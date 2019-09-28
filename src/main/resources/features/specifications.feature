Feature: Using the booking website

  Scenario Outline: Booking works
    Given I want to use the booking website
    When I try to create a <booking type> booking
    Then that booking is <accepted or not>

    Examples: basic booking validity
      | booking type | accepted or not |
      | valid        | accepted        |
      | invalid      | not accepted    |

  @pending
  Scenario: Existing bookings are displayed on page load
    Given I want to use the booking website
    When I try to create a valid booking
    Then that booking persists on page reload

  @pending
  Scenario: Bookings can be deleted