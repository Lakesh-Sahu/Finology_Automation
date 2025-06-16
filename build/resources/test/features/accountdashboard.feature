@account_dashboard
Feature:
  Account Dashboard Functionality

  Background:
    Given User is logged in and on account dashboard page

  Scenario: All dashboard element should be displayed on dashboard page
    Given User should be on account dashboard page
    When User should be on account dashboard page
    Then All dashboard elements should be displayed
    And User should perform logout from account dashboard page

  Scenario: All user photo element should be displayed on dashboard page
    Given User should be on account dashboard page
    When User clicks user profile
    Then All user photo elements should be displayed
    And User should perform logout from account dashboard page

  Scenario Outline: Welcome message should be displayed on dashboard page
    Given User should be on account dashboard page
    When User should be on account dashboard page
    Then Welcome "<message>" should be displayed

    Examples:
      | message                                                              |
      | Welcome to Finology. Access our state of the art products from here. |