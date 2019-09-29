@network
Feature: The booking service network API
  These are still very high level specifications, for
  detailed and data driven testing use a a different
  approach such as contract testing.

  Scenario: bookings can be made
    Given I make a valid booking
    When I try to retrieve that booking
    Then the booking exists