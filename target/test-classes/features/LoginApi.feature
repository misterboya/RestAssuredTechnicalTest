Feature: Login API

  Scenario: Validate login is successful
    Given login payload with external json file
    When user calls POST_LOGIN with POST http request
    Then the API call got success with status code 200
