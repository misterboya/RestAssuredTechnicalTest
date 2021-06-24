Feature: List resource API

  Scenario: Validate list resource end point is getting correct data
    When user calls GET end point status code is 200
    Then response body should contain id



