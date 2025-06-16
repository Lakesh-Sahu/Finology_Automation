@login
Feature:
  Login Functionality

  Background:
    Given User is on new login page

  @unsuccessful_login
  Scenario Outline: Unsuccessful login with invalid or empty mobile number
    Given User enter "<mobile number>" in mobile number input box
    When User clicks Continue button
    Then Invalid mobile or required error "<message>" should be shown
    And User should be on login page

    Examples:
      | mobile number | message        |
      | @$#637*       | Invalid Mobile |
      |               | *Required      |

  @unsuccessful_login_with_new_mobile
  Scenario: Unsuccessful login with new mobile number
    Given Generate new mobile number and User enter in mobile number input box
    When User clicks Continue button
    Then User should be redirected to sign up page

  @successful_login
  Scenario Outline: Successful login with registered mobile number, email and password
    Given User enter "<mobile number>" in mobile number input box
    When User clicks Continue button
    Then User should be redirected to Login Auth Page
    And User enter "<mobile number or email>" in the mobile or email input box and "<password>" in the password input box
    And User clicks login button
    And  User should be redirected to account dashboard page
    And User should perform logout from account dashboard page

    Examples:

      | mobile number | mobile number or email  | password           |
      | 5846975120    | 5846975120              | finTestUserAcology |
      | 5846975120    | testuserac121@gmail.com | finTestUserAcology |

  @successful_login2
  Scenario Outline: Successful login with registered mobile number, email and password by clicking Login with Email button
    Given User should be on login page
    When User clicks Login with Email button
    Then User should be redirected to Login Auth Page
    And User enter "<mobile number or email>" in the mobile or email input box and "<password>" in the password input box
    And User clicks login button
    And  User should be redirected to account dashboard page
    And User should perform logout from account dashboard page

    Examples:

      | mobile number or email  | password           |
      | 5846975120              | finTestUserAcology |
      | testtuserac121@gmail.com | finTestUserAcology |
