Feature: Testing known regressions

  These scenarios check for know regressions. Execution frequency should depend on risk,
  e.g. if an issue has not occurred for a while then scenario does not need to be run on
  every commit but should be run before release.

  Scenario: Check-out should be after check-in
    Given I want to use the booking website
    When I try to create a booking with the check-out date before the check-in date
    Then that booking is not accepted
