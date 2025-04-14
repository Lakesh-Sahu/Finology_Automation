Feature: Sign Up Functionality

  Background: Given User should be on sign up page

  Scenario Outline: Unsuccessful sign up with invalid name
    Given User enter invalid "<name>", valid "<email id>" and valid "<mobile number>"
    When User clicks sign up button
    Then Error "<message>" should be shown below name input box
    And User should not be sign up

    Examples:
      | name         | email id                       | mobile number | message                               |
      | @Lakesh Sahu | randomNewEmail2423@gmail.com   | 9868546793    | Your name can contain only alphabets. |
      | Raj$User     | randomNewEmailMe2123@gmail.com | 1257465983    | Your name can contain only alphabets. |

  Scenario Outline: Unsuccessful sign up with invalid name
    Given User enter invalid "<name>", valid "<email id>" and valid "<mobile number>"
    When User clicks sign up button
    Then Error "<message>" should be shown below name input box
    And User should not be sign up

    Examples:
      | name         | email id                       | mobile number | message                               |
      | @Lakesh Sahu | randomNewEmail2423@gmail.com   | 9868546793    | Your name can contain only alphabets. |
      | Raj$User     | randomNewEmailMe2123@gmail.com | 1257465983    | Your name can contain only alphabets. |

  Scenario Outline: Unsuccessful sign up with invalid email id
    Given User enter valid "<name>", invalid "<email id>" and valid "<mobile number>"
    When User clicks sign up button
    Then Error "<message>" should be shown below email input box
    And User should not be sign up

    Examples:
      | name        | email id                       | mobile number | message                               |
      | Lakesh Sahu | randomNewEmail2423gmail.com    | 9868546793    | Your name can contain only alphabets. |
      | Raj User    | randomNewEmailMe2123@gmail.com | 1257465983    | Your name can contain only alphabets. |