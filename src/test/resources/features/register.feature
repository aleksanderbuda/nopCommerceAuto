Feature: User Registration
  As a new user
  I want to register for an account
  So I can access the application's features

  Background:
    Given the user is on the home page
    Then the user navigates to registration page

  @positive
  Scenario: Successful registration with random data
    When the user selects gender
    And the user fills first name with random value
    And the user fills last name with random value
    And the user fills email with random value
    And the user fills company with "Alex Company"
    And the user fills password with random value
    And the user clicks the register button
    Then the registration should be successful

  @negative @required_fields
  Scenario: Registration with all required fields empty
    When the user clicks the register button
    Then all required field errors should be visible

  @negative @individual_fields
  Scenario Outline: Registration with missing required field
    When the user fills all fields except "<excluded_field>"
    And the user clicks the register button
    Then the registration should be successful

    Examples:
      | excluded_field | field_error     |
      | first name     | first name      |
      | last name      | last name       |
      | email          | email           |
      | password       | password        |

  @negative @invalid_email
  Scenario Outline: Registration with invalid email formats
    When the user fills email with "<email>"
    And the user clicks the register button
    Then the email error should be visible

    Examples:
      | email             |
      | invalid-email     |
      | test@domain       |
      | test@.com         |