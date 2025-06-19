@Home @Regression

Feature: Purchase a book from automationpractice.in

  Background:
    Given the user opens the browser
    And the user navigates to the URL "http://practice.automationtesting.in/"


  @HomePageArrivals
  Scenario: Home page shows 3 Arrivals
    Then the Home page shows only 3 Arrivals

  @HomePageSliders
  Scenario: Home page has 3 Sliders
    Then the Home page has 3 Sliders only
    And the Home page must contain only 3 Sliders

  @BookDetailAddToBasket
  Scenario: Book detail page displays Add to Basket button
    When the user clicks on the image of any Arrival
    Then the book detail page is displayed with Add to Basket button

  @AddBookAndNavigatePayment @TpFinalJ
  Scenario: Add book to basket and navigate to payment page (without placing order)
    When the user clicks on the image of any Arrival
    And the book detail page is displayed with Add to Basket button
    And the user adds a book to the basket
    And the user clicks view basket
    And the user proceeds to checkout
    Then the total should be less than the subtotal
    And the payment page should show Billing Details, Order Details, Additional Details, and Payment gateway options

  @CompleteOrderAndVerifyConfirmation @TpFinalJ
  Scenario: Complete the order process and verify order confirmation page
    When the user clicks on the image of any Arrival
    And the book detail page is displayed with Add to Basket button
    And the user adds a book to the basket home
    And the user clicks add basket home
    And the user clicks view basket home
    And the user proceeds to checkout home
    And the user autocompletes the billing form with country "India" and state "Telangana"
    And the user selects payment method "Cash on Delivery"
    And the user clicks on "Place Order" button home
    Then the order is completed
    And the order confirmation page shows order, bank, customer, and billing details

