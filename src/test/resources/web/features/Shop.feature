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

  @TpFinal1
  Scenario Outline: Validate Tax and Price Details
    When the user adds a book to the basket
    And the user clicks view basket
    And the user proceeds to checkout
    Then the subtotal and total values are displayed
    And the total should be greater than the subtotal
    And the user fills the billing form with country "<country>"
    And the tax rate should be <taxRate>% for "<country>"

    Examples:
      | country   | taxRate |
      | India     | 2       |
      | Argentina | 5       |




  @TpFinal12
  Scenario Outline: Add book to basket and proceed to checkout
    When the user adds a book to the basket
    And the user clicks view basket
    And the user proceeds to checkout
    And the user selects payment method "<paymentMethod>"
    And the user clicks on the "Place Order" button shop
    Then the user can view that book in the basket menu item with price
    And the user navigates to the checkout page
    And the user can see total and subtotal values just above the "Proceed to Checkout" button
    And the total value is less than the subtotal value
    And the user navigates to the payment gateway page
    And the order is completed
    And the user is navigated to the Order Confirmation page
    And the user can see order details, bank details, customer details, and billing details

    Examples:
      | paymentMethod            |
      | Direct bank transfer     |
      | Check Payments           |
      | Cash on Delivery         |
      | PayPal                   |







