
Feature: User registration on the website

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"
    And the user clicks on the "My Account" menu

  @Smoke
  Scenario: User registration successful
    When the user enters "marinaracelilopez@gmail.com" in the "registration email" field
    And the user enters "Marina_12_26" in the "registration password" field
    And the user clicks on the "register" button
    Then the user should see a registration confirmation or welcome message

  Scenario Outline: Invalid registration with incorrect combinations
    When the user enters "<email>" in the "registration email" field
    And the user enters "<password>" in the "registration password" field
    And the user clicks on the "register" button
    Then the user should stay on the same page
    And a message saying "<message>" should be displayed

    Examples:
      | email                      | password      | message                                                                 |
      | assdg-@gmail               | Marina_12_26  | Please provide a valid email address.                                   |
      |                            | Marina_12_26  | Please provide a valid email address.                                   |
      |                            |               | Please provide a valid email address.                                   |
      | marinaracelilopez@gmail.com|               | An account is already registered with your email address. Please login. |
