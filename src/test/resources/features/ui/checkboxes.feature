Feature: Checkboxes

  @ui
  Scenario: Toggle first checkbox
    Given I open Checkboxes page
    When I toggle checkbox at index 0
    Then checkbox at index 0 should be toggled
