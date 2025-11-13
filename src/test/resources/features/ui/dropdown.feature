Feature: Dropdown

  @ui
  Scenario: Select Option 2
    Given I open Dropdown page
    When I choose "Option 2"
    Then selected option should be "Option 2"
