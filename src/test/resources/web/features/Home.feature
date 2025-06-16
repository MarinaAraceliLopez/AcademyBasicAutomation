Feature: Purchase a book from automationpractice.in

  Scenario: Add book to basket and navigate to payment page (sin completar orden)
    Given the browser is open
    When the user navigates to "http://practice.automationtesting.in/"
    And the user clicks on "Shop" menu
    And the user clicks on "Home" menu button
    Then the Home page should show only 3 Arrivals
    When the user clicks on the image of any Arrival
    Then the user should be navigated to the book detail page with Add to Basket button
    When the user clicks on "Add To Basket" button
    Then the basket menu item should display the added book with price
    When the user clicks on the basket menu item link
    Then the checkout page should display total and subtotal values with total < subtotal
    When the user clicks on "Proceed to Checkout" button
    Then the payment page should display Billing Details, Order Details, Additional Details, and Payment gateway options
    When the user fills billing details with valid data
    And the user selects any payment method
    And the user applies a coupon if available

  Scenario: Complete the order process and verify order confirmation page
    Given the browser is open
    When the user navigates to "http://practice.automationtesting.in/"
    And the user clicks on "Shop" menu
    And the user clicks on "Home" menu button
    Then the Home page should show only 3 Arrivals
    When the user clicks on the image of any Arrival
    Then the user should be navigated to the book detail page with Add to Basket button
    When the user clicks on "Add To Basket" button
    Then the basket menu item should display the added book with price
    When the user clicks on the basket menu item link
    Then the checkout page should display total and subtotal values with total < subtotal
    When the user clicks on "Proceed to Checkout" button
    Then the payment page should display Billing Details, Order Details, Additional Details, and Payment gateway options
    When the user fills billing details with valid data
    And the user selects any payment method
    And the user applies a coupon if available
    When the user clicks on "Place Order" button
    Then the order confirmation page should display order, bank, customer, and billing details
