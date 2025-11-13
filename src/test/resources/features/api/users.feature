Feature: Users API smoke

  @api @smoke
  Scenario: List users page 2
    When I GET "/api/users?page=2"
    Then status should be 200
    And json path "data" is non-empty array

  @api
  Scenario: Create user
    When I POST "/api/users" with JSON:
      """
      {"name":"morpheus","job":"leader"}
      """
    Then status is one of 200,201
    And json path "name" equals "morpheus"
