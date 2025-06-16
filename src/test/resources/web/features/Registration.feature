   @Regression
  Feature: User registration on the website

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"
    And the user clicks on the "My Account" menu

    @Registration
    Scenario: User registration successful
      When the user enters "testeando12345@gmail.com" in the "registration email" field
      And the user enters "testeando12345" in the "registration password" field
      And the user clicks on the "register" button
      Then the user should see a registration confirmation or welcome message


    @TpFinal
  Scenario Outline: Invalid registration with incorrect combinations
    When the user enters "<email>" in the "registration email" field
    And the user enters "<password>" in the "registration password" field
    And the user clicks on the "register" button
    Then the user should stay on the same page
    And a message saying "<message>" should be displayed

    Examples:
      | email                      | password      | message                                                                        |
      | assdg-@gmail               | testeando1234 | Please provide a valid email address.                                          |
      |                            | testeando1234 | Please provide a valid email address.                                          |
      |                            |               | Please provide a valid email address.                                          |
      | testeando1234@gmail.com    |               | Error: An account is already registered with your email address. Please login. |


