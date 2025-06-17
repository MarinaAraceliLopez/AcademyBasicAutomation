@MyAccount @Regression

Feature: My Account

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"
    And the user clicks on the "My Account" menu
    And the user enters "testeando1234@gmail.com" in the "login email" field
    And the user enters "testeando1234" in the "login password" field
    And the user clicks on the "login" button
    And the user should see "Hello"


  Scenario Outline: Verify account functionalities after login for "<action>"
    When the user clicks on the "<action>" button
    Then the user should see the "<action>" section visible

    Examples:
      | action          |
      | Account Details |
      | Addresses       |
      | Orders          |


  Scenario: Edit Shipping Address
    When the user clicks on the "Addresses" button
    And the user clicks on the "Edit" button for the "Shipping Address"
    Then the shipping address form should be visible


  Scenario: View Order Details
    Given the user has at least one order in their account
    When the user clicks on the "Orders" button
    And the user clicks on the "View" button of an order
    Then the user should see the order detail

  @TpFinal
  Scenario: Logout Flow
    When the user clicks on the "My Account" menu
    And the user clicks on the "Logout" button
    Then the user should be logged out successfully

  #@TpFinal
  #Scenario: View Account Details and Change Password
    #When the user clicks on the "My Account" menu
  #  And the user clicks on the "Account Details" button
   # Then the user should see the account details section
  #  And the user should be able to change the password

  #@MyAccount@TpFinal
 # Scenario: Change and Restore Password
   # When the user clicks on the "My Account" menu
    #When the user clicks on the "Account Details" button
    #And the user changes password from "Marina_12_26" to "NewPassword123!"
    #And the user changes password from "NewPassword123!" to "Marina_12_26"
    #Then the password should be successfully updated

