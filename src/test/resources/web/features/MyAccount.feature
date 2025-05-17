@MyAccount @Regression

Feature: My Account

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"
    And the user clicks on the "My Account" menu
    And the user enters "marinaracelilopez@gmail.com" in the "login email" field
    And the user enters "Marina_12_26" in the "login password" field
    And the user clicks on the "LOGIN" button
    Then the user should see "Hello"

  @Pending
  Scenario Outline: Verify account functionalities after login for "<action>"
    When the user clicks on the "<action>" button
    Then the user should see the "<action>" section visible

    Examples:
      | action          |
      | Account Details |
      | Addresses       |
      | Orders          |


  @Pending @Ignore
  Scenario: Edit Shipping Address
    When the user clicks on the "Addresses" button
    And the user clicks on the "Edit" button for the "Shipping Address"
    Then the shipping address form should be visible

  @Ignore
  Scenario: View Order Details
    Given the user has at least one order in their account
    When the user clicks on the "Orders" button
    And the user clicks on the "View" button of an order
    Then the user should see the order detail

  Scenario: Logout
    When the user clicks on the "Logout" button
    Then the user should be logged out successfully

