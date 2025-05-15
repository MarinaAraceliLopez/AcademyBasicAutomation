@Pruebas
Feature: Shop

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"
    And the user clicks on the "Shop" menu

  Scenario: Filter products by price range between 350 and 400 rupees
    When the user adjusts the price filter between 350 and 400 rupees
    And the user clicks on the Filter button
    Then the user should see only books priced between 350 and 400 rupees

  Scenario Outline: Filter products by "<sortingOption>"
    When the user selects "<sortingOption>" from the sorting dropdown
    Then the user should see the popular products

    Examples:
      | sortingOption               |
      | Sort by newness             |
      | Sort by popularity          |
      | Sort by average rating      |

  Scenario: The user clicks on the first sale product on the home page
    When the user clicks on the "first" "Sale" product on the home page
    Then the user can clearly view the actual price with the old price struck through

  Scenario: The user clicks on the second sale product on the home page
    When the user clicks on the "second" "Sale" product on the home page
    Then the user can clearly view the actual price with the old price struck through



