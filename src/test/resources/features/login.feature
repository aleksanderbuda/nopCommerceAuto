Feature: User Login Functionality

  Background:
    Given the user is on the login page

  @positive
  Scenario Outline: Successful login with valid credentials
    When the user attempts to login with email "<email>" and password "<password>"
    Then the user should be redirected to the home page

    Examples:
      | email              | password       |
      | valid@example.com  | ValidPass123   |

  @negative @credentials
  Scenario Outline: Login with invalid credentials
    When the user attempts to login with email "<email>" and password "<password>"
    Then the invalid credentials error should be displayed
    And the password field should be empty

    Examples:
      | email                 | password       |
      | valid@example.com     | WrongPassword  |
      | unregistered@test.com | AnyPassword    |

  @negative @email_format
  Scenario Outline: Login with invalid email formats
    When the user attempts to login with email "<email>" and password "<password>"
    Then the email field error should be displayed

    Examples:
      | email             | password  |
      | invalid-email     | AnyPass   |
      | test@domain       | AnyPass   |
      | test@.com         | AnyPass   |