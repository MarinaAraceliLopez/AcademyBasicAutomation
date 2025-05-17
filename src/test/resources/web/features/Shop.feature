@Shop @Regression
Feature: Shop

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"
    And the user clicks on the "Shop" menu

  @Filter
  Scenario: Filter products by price range between 150 and 450 rupees
    When the user adjusts the price filter between 150 and 450 rupees
    And the user clicks on the Filter button
    Then the user should see only books priced between 150 and 450 rupees

  @Filter
  Scenario Outline: Filter products by "<sortingOption>"
    When the user selects "<sortingOption>" from the sorting dropdown
    Then the user should see the popular products

    Examples:
      | sortingOption               |
      | Sort by newness             |
      | Sort by popularity          |
      | Sort by average rating      |

  @SaleProducts
  Scenario: The user clicks on the first sale product on the home page
    When the user clicks on the "first" "Sale" product on the home page
    Then the user can clearly view the actual price with the old price struck through

  @SaleProducts
  Scenario: The user clicks on the second sale product on the home page
    When the user clicks on the "second" "Sale" product on the home page
    Then the user can clearly view the actual price with the old price struck through



