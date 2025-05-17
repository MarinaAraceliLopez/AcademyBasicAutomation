@Login @Regression
Feature: Login

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"
    And the user clicks on the "My Account" menu

  @Smoke
  Scenario: Successful user login
    When the user enters "marinaracelilopez@gmail.com" in the "login email" field
    And the user enters "Marina_12_26" in the "login password" field
    And the user clicks on the "login" button
    Then the user should be redirected to the home page

  Scenario Outline: Invalid login with incorrect combinations
    When the user enters "<email>" in the "login email" field
    And the user enters "<password>" in the "login password" field
    And the user clicks on the "login" button
    Then the user should stay on the same page
    And a message saying "<message>" should be displayed

    Examples:
      | email                       | password      | message                                                                                                                        |
      | assdg-@gmail                | 123mal-       | The username assdg-@gmail is not registered on this site. If you are unsure of your username, try your email address instead.  |
      | marinaracelilopez@gmail.com |               | Password is required.                                                                                                          |
      |                             | Marina_12_26  | Username is required.                                                                                                          |
