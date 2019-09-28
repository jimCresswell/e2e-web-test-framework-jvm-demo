Feature: Using the booking website

  Background: The user is visiting the booking site
    Given I want to use the booking website


  Scenario Outline: Bookings are handled according to validity
    When I try to create a <booking type> booking
    Then that booking is <accepted or not>

    Examples: basic booking validity
      | booking type | accepted or not |
      | valid        | accepted        |
      | invalid      | not accepted    |


  Scenario: Existing bookings are displayed on page load
    When there is a booking in the system
    Then that booking displays on page load


  @debug
  Scenario: Bookings can be deleted
    And there is a booking in the system
    When I delete that booking
    Then the booking is removed from the booking list