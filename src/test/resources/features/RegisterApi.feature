Feature: Register API

  Scenario: Validate registration is successful
    Given register with payload from external json
    When user calls POST_REGISTER should return status 200

