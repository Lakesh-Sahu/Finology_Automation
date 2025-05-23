@Signup
Feature:
  Sign Up Functionality

  Background:
    Given User is on new sign up page

  @invalid_name_signup
  Scenario Outline: Unsuccessful sign up with invalid name, valid email id and valid mobile number
    Given User enters "<name>", "<email id>" and "<mobile number>"
    When User clicks sign up button
    Then Invalid name Error "<message>" should be shown below name input box
    And User should not be redirected to otp verification page

    Examples:
      | name         | email id                       | mobile number | message                               |
      | @Lakesh Sahu | randomNewEmail2423@gmail.com   | 9868546793    | Your name can contain only alphabets. |
      | Raj$User12   | randomNewEmailMe2123@gmail.com | 1257465983    | Your name can contain only alphabets. |

  @invalid_name_signup
  Scenario Outline: Unsuccessful sign up with invalid name, valid email id and valid mobile number
    Given User enters "<name>", "<email id>" and "<mobile number>"
    When User clicks sign up button
    Then Empty name Error "<message>" should be shown below name input box
    And User should not be redirected to otp verification page

    Examples:
      | name | email id                | mobile number | message   |
      |      | lakeshsahu758@gmail.com | 5784692345    | *Required |

  @invalid_email_signup
  Scenario Outline: Unsuccessful sign up with valid name, invalid email id and valid mobile number
    Given User enters "<name>", "<email id>" and "<mobile number>"
    When User clicks sign up button
    Then Error "<message1>" or "<message2>" should be shown below email input box
    And User should not be redirected to otp verification page

    Examples:
      | name      | email id             | mobile number | message1      | message2                            |
      | Rita      | rita2056gmail.com    | 9868546756    | Invalid Email | Please enter a valid email address. |
      | Ram Shyam | shyammaster675@gmail | 1257465943    | Invalid Email | Please enter a valid email address. |
      | Hitesh    |                      | 8646597458    | *Required     | *Required                           |

  @successful_signup
  Scenario Outline: Successful sign up with valid name, valid email and valid mobile number
    Given User enters "<name>", "<email id>" and "<mobile number>"
    When User clicks sign up button
    Then User should redirect to OTP verification page
#    Then User should see incognito mode error message "<message>" or redirected to OTP verification page
    And Finology logo, home button, enter otp text, otp input box, verify otp button should be displayed on OTP verification page

    Examples:
      | name                    | email id                                       | mobile number | message                                                        |
      | TestUserRieeeeeeeeeeeee | testuserrieeeeeeeeeeeee2726@gmail.com          | 9437525638    | Signup is unsupported in incognito. Please try in normal mode. |
      | Reriserrrrrrrrrrrrrr    | reriserrrrrrrrrrrrrrruserEmailMe2122@gmail.com | 8335957041    | Signup is unsupported in incognito. Please try in normal mode. |

  @unsuccessful_duplicate_account_signup
#    Examples data is coming from the above successful examples (applying with different combinations)
  Scenario Outline: Unsuccessful sign up with valid name, duplicate email or duplicate mobile number
    Given User enters "<name>", "<email id>" and "<mobile number>"
    When User clicks sign up button
    Then User should be redirected to login page
    And Already account "<message>" should be shown
#    Then User should be redirected to OTP verification page or User should be redirected to login page or Already account "<message>" should be shown or User should see incognito mode error message "<message2>"

    Examples:
      | name                    | email id                                       | mobile number | message                                                | message2                                                       |
      | TestUserRieeeeeeeeeeeee | testuserrieeeeeeeeeeeee2726@gmail.com          | 5598705134    | You already have an account. Please Login to continue. | Signup is unsupported in incognito. Please try in normal mode. |
      | Reriserrrrrrrrrrrrrr    | otherneewuueeemail211317@gmail.com             | 8335957041    | You already have an account. Please Login to continue. | Signup is unsupported in incognito. Please try in normal mode. |
      | Reriserrrrrrrrrrrrrr    | reriserrrrrrrrrrrrrrruserEmailMe2122@gmail.com | 8335957041    | You already have an account. Please Login to continue. | Signup is unsupported in incognito. Please try in normal mode. |