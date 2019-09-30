Feature: The booking service network API
  These are still very high level specifications, for
  detailed and data driven testing use a a different
  approach such as contract testing.
  
  Scenario: bookings can be made using the API
    Given I make a valid booking
    When I try to retrieve that booking
    Then the booking exists

  @pending
  Scenario: large numbers of bookings are possible
    The team should discuss what "large" means in this context.

    Given the system contains a large number of bookings
    When I try to make another booking
    Then the booking exists