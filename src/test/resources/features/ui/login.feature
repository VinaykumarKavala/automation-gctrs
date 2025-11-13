Feature: Login flows

  @ui @smoke
  Scenario: Valid login
    Given I open the Login page
    When I login with configured credentials
    Then I should reach Secure Area
