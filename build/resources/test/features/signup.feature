Feature: Sign Up Functionality

    Background: Given User should be on sign up page

      Scenario Outline: Unsuccessful sign up with invalid name
        Given User enter invalid "<name>", valid "<email id>" and valid "<mobile number>"
        When User clicks sign up button
        Then Error "<message>" should be shown
        And User should not be sign up

        Examples:
        | name        | email id                       | mobile number | message                               |
        | @Lakesh Sahu| randomNewEmail2423@gmail.com   | 9868546793    | Your name can contain only alphabets. |
        | Raj$User    | randomNewEmailMe2123@gmail.com | 1257465983    | Your name can contain only alphabets. |