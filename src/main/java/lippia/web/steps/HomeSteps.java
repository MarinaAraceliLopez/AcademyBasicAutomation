package lippia.web.steps;


import com.crowdar.driver.DriverManager;
import io.cucumber.java.en.*;

import lippia.web.services.HomeService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

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

//    @When("the user selects any payment method")
//    public void selectPayment() {
//        HomeService.selectPaymentMethod("bank transfer");
//    }


    @When("the user clicks on Place Order button")
    public void clickPlaceOrder() {
        HomeService.clickPlaceOrder();
    }

//    @Then("the order confirmation page should display order, bank, customer, and billing details")
//    public void verifyOrderConfirmation() {
//        assertTrue(HomeService.isOrderConfirmationPageVisible(), "Order confirmation page not displayed");
//    }

    @When("the user selects any payment method")
    public void selectAnyPaymentMethod() {
        HomeService.selectPaymentMethod("bank transfer"); // o el que aparezca en tu aplicación
    }

    @When("the user applies a coupon if available")
    public void applyCouponIfAvailable() {
        HomeService.applyCoupon("DISCOUNT10"); // o el código de cupón que tengas disponible
    }

    @When("the user clicks on {string} button")
    public void clickPlaceOrderBtn(String button) {
        if (button.equalsIgnoreCase("Place Order")) {
            HomeService.clickPlaceOrder();
        } else {
            throw new IllegalArgumentException("Unknown button: " + button);
        }
    }

    @Then("the order confirmation page should display order, bank, customer, and billing details")
    public void verifyOrderConfirmationPage() {
        assertTrue(HomeService.isOrderConfirmationPageVisible(), "Order confirmation page not visible.");
    }

    @When("the user navigates to {string}")
    public void navigateToUrl(String url) {
        HomeService.openUrl(url);
    }

    @When("the user clicks on {string} menu button")
    public void clickMenuButton(String menuName) {
        if (menuName.equalsIgnoreCase("Home")) {
            HomeService.clickHomeMenu();
        } else {
            throw new IllegalArgumentException("Menu button not recognized: " + menuName);
        }
    }

    @Then("the total should be less than the subtotal")
    public void totalShouldBeLessThanSubtotal() {
        double total = HomeService.getTotalValue();
        double subtotal = HomeService.getSubtotalValue();
        assertTrue(total < subtotal, "Total is not less than subtotal.");
    }

    @Then("the payment page should show Billing Details, Order Details, Additional Details, and Payment gateway options")
    public void verifyPaymentPageSectionsVisible() {
        assertTrue(HomeService.isBillingDetailsVisible(), "Billing details not visible.");
        assertTrue(HomeService.isOrderDetailsVisible(), "Order details not visible.");
        assertTrue(HomeService.isAdditionalDetailsVisible(), "Additional details not visible.");
        assertTrue(HomeService.isPaymentGatewayVisible(), "Payment gateway options not visible.");
    }

    @Then("the order confirmation page shows order, bank, customer, and billing details")
    public void verifyOrderConfirmationPageVisible() {
        assertTrue(HomeService.isOrderDetailsVisibleOnConfirmation(), "Order details not visible.");
        assertTrue(HomeService.isBankDetailsVisible(), "Bank details not visible.");
        assertTrue(HomeService.isCustomerDetailsVisible(), "Customer details not visible.");
        assertTrue(HomeService.isBillingDetailsVisibleConfirmation(), "Billing details not visible.");
    }
    @Then("the Home page shows only 3 Arrivals")
    public void homePageShowsThreeArrivals() {
        int arrivals = HomeService.getArrivalCount();
        assertEquals(3, arrivals, "Expected 3 Arrivals on Home page.");
    }

    @Then("the Home page has 3 Sliders only")
    public void homePageHasThreeSliders() {
        int sliders = HomeService.getSlidersCount();
        assertEquals(3, sliders, "Expected 3 Sliders on Home page.");
    }

    @Then("the Home page must contain only 3 Sliders")
    public void homePageMustContainThreeSliders() {
        int sliders = HomeService.getSlidersCount();
        assertEquals(3, sliders, "Expected exactly 3 Sliders.");
    }


    @Then("the book detail page is displayed with Add to Basket button")
    public void bookPageShouldShowAddToBasketButton() {
        WebDriver driver = DriverManager.getDriverInstance();

        By addToBasketBtn = By.xpath("//button[contains(text(), 'Add to basket')]");

        boolean isVisible = !driver.findElements(addToBasketBtn).isEmpty()
                && driver.findElement(addToBasketBtn).isDisplayed();

        if (!isVisible) {
            fail("Add to basket button not visible!");
        }

        @When("the user clicks on the image of any Arrival")
        public void clickAnyProductImage(){
            HomeService.clickProduct(3);
        }
        @Then("the book detail page is displayed with Add to Basket button")
        public void bookPageShouldShowAddToBasketButton() {
            assertTrue("Add to basket button not visible!", HomeService.isAddToBasketVisible());
        }


    }














}
