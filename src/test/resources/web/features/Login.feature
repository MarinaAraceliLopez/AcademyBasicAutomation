@Login @Regression
Feature: Login

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"
    And the user clicks on the "My Account" menu

  @InvalidCredentials
  Scenario Outline: Invalid login with incorrect combinations
    When the user enters "<email>" in the "login email" field
    And the user enters "<password>" in the "login password" field
    And the user clicks on the "login" button
    Then the user should stay on the same page
    And a message saying "<message>" should be displayed

    Examples:
      | email                   | password      | message                                                                                                                        |
      | assdg-@gmail            | 123mal-       | The username assdg-@gmail is not registered on this site. If you are unsure of your username, try your email address instead.  |
      | testeando1234@gmail.com |               | Password is required.                                                                                                          |
      |                         | testeando1234 | Username is required.                                                                                                          |

  @CaseSensitivity @TpFinal
  Scenario: Login fails with case changed username and password
    When the user enters "testeando@gmail.com" in the "login email" field
    And the user enters "testeando" in the "login password" field
    And the user clicks on the "login" button
    Then the user should stay on the same page
    And a message saying "Error: A user could not be found with this email address." should be displayed

  @Security @BackNavigation @TpFinal
  Scenario: User cannot access account after logout using back button
    When the user enters "testeando1234@gmail.com" in the "login email" field
    And the user enters "testeando1234" in the "login password" field
    And the user clicks on the "login" button
    And the user logs out
    And the user presses the browser back button
    Then the user should not be signed in
    And the user should see the login page

  @UserLogin
  Scenario: Successful user login
    When the user enters "testeando1234@gmail.com" in the "login email" field
    And the user enters "testeando1234" in the "login password" field
    And the user clicks on the "login" button
    Then the user should be redirected to the home page
