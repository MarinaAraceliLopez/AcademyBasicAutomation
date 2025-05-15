Feature: My Account

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"
    And the user clicks on the "My Account" menu
    And the user enters "marinaracelilopez@gmail.com" in the "login email" field
    And the user enters "Marina_12_26" in the "login password" field
    And the user clicks on the "LOGIN" button
    And the user should see "Welcome to your account dashboard"

  Scenario Outline: Verify account functionalities after login for "<action>"
    When the user clicks on the "<action>" button
    Then the user should see "<resultMessage>"

    Examples:
      | action             | resultMessage                                      |
      | Logout             | You have been logged out successfully              |
      | Account Details    | Here you can edit your account and change password |
      | Address            | Your billing and shipping addresses are shown      |
      | Orders             | User must view their orders on clicking orders link|

  Scenario: Edit on Shipping Address
    When the user clicks on the "Address" button
    And the user clicks on the "Edit" button for the "Shipping Address"
    Then the user can edit shipping address

