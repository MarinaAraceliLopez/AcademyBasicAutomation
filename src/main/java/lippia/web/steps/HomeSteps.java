package lippia.web.steps;


import io.cucumber.java.en.*;
import lippia.web.services.HomeService;
import static org.testng.Assert.*;

public class HomeSteps  {

    @Given("the browser is open")
    public void openBrowser() {
        // browser opening managed externally or by driver setup
    }

//    @When("the user navigates to {string}")
//    public void navigateToUrl(String url) {
//        HomeService.openUrl(url);
//    }

    @When("the user clicks on {string} menu")
    public void clickMenu(String menuName) {
        switch (menuName.toLowerCase()) {
            case "shop":
                HomeService.clickShopMenu();
                break;
            case "home":
                HomeService.clickHomeMenu();
                break;
            default:
                throw new IllegalArgumentException("Menu not recognized: " + menuName);
        }
    }

    @Then("the Home page should show only 3 Arrivals")
    public void verifyThreeArrivals() {
        int count = HomeService.countArrivals();
        assertEquals(count, 3, "Home page does not show exactly 3 arrivals");
    }

    @When("the user clicks on the image of any Arrival")
    public void clickArrivalImage() {
        HomeService.clickArrivalImage(1); // click first arrival
    }

    @Then("the user should be navigated to the book detail page with Add to Basket button")
    public void verifyAddToBasketVisible() {
        assertTrue(HomeService.isAddToBasketButtonVisible(), "Add to Basket button not visible");
    }

    @When("the user clicks on Add To Basket button")
    public void clickAddToBasket() {
        HomeService.clickAddToBasket();
    }

    @Then("the basket menu item should display the added book with price")
    public void verifyBookInBasket() {
        assertTrue(HomeService.isBookInBasketWithPrice(), "Book not visible in basket with price");
    }

    @When("the user clicks on the basket menu item link")
    public void clickBasketMenu() {
        HomeService.clickBasketItemLink();
    }

    @Then("the checkout page should display total and subtotal values with total < subtotal")
    public void verifyTotalVsSubtotal() {
        double total = HomeService.getTotalValue();
        double subtotal = HomeService.getSubtotalValue();
        assertTrue(total < subtotal, "Total is not less than subtotal");
    }

    @When("the user clicks on Proceed to Checkout button")
    public void clickProceedToCheckout() {
        HomeService.clickProceedToCheckout();
    }

    @Then("the payment page should display Billing Details, Order Details, Additional Details, and Payment gateway options")
    public void verifyPaymentPage() {
        assertTrue(HomeService.isPaymentPageVisible(), "Payment page not displayed correctly");
    }

    @When("the user fills billing details with valid data")
    public void fillBillingDetails() {
        HomeService.fillBillingDetails("Juan", "juan@example.com", "1234567890", "Calle Falsa 123");
    }

    @When("the user selects any payment method")
    public void selectPayment() {
        HomeService.selectPaymentMethod("bank transfer");
    }

    @When("the user applies a coupon if available")
    public void applyCouponIfAvailable() {
        HomeService.applyCoupon("DISCOUNT10");
    }

    @When("the user clicks on Place Order button")
    public void clickPlaceOrder() {
        HomeService.clickPlaceOrder();
    }

    @Then("the order confirmation page should display order, bank, customer, and billing details")
    public void verifyOrderConfirmation() {
        assertTrue(HomeService.isOrderConfirmationPageVisible(), "Order confirmation page not displayed");
    }
}
