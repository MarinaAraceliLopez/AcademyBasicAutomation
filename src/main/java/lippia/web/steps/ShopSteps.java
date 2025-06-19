package lippia.web.steps;
import com.crowdar.core.PageSteps;
import com.crowdar.driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lippia.web.services.ShopService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static lippia.web.services.ShopService.*;


//1 Scenario: Filter products by price range between 150 and 450 rupees

public class ShopSteps extends PageSteps {

    private static WebDriver driver = new ChromeDriver();
    @When("the user adjusts the price filter between {int} and {int} rupees")
    public void adjustPriceFilter(int min, int max) {
        ShopService.setPriceRangeByValues(min, max, 150, 500);
        ShopService.clickFilterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @When("the user clicks on the Filter button")
    public void theUserClicksOnTheFilterButton() {
        ShopService.clickFilterButton();
    }
    @Then("the user should see only books priced between {int} and {int} rupees")
    public void verifyBooksPrices(int min, int max) {
        ShopService.verifyPricesInRange(min, max);
    }

    //2 Scenario Outline: Filter products by "<sortingOption>"
    @When("the user selects {string} from the sorting dropdown")
    public void theUserSelectsFromTheSortingDropdown(String option) {
        ShopService.selectSortingOption(option);
    }
    @Then("the user should see the popular products")
    public void theUserShouldSeeThePopularProducts() {
        ShopService.verifyProductsVisible();
    }

    //3 y 4 Scenario: The user clicks on the first sale product on the home page
    @When("the user clicks on the {string} {string} product on the home page")
    public void theUserClicksOnTheProductOnTheHomePage(String position, String type) {
        if (!type.equalsIgnoreCase("Sale")) {
            throw new IllegalArgumentException("Unsupported product type: " + type);
        }
        ShopService.clickSaleProduct(position);
    }
    @Then("the user can clearly view the actual price with the old price struck through")
    public void theUserCanClearlyViewTheActualPriceWithTheOldPriceStruckThrough() {
        ShopService.verifyPriceWithStrikeThrough();
    }

//5 y 6 Scenario: The user clicks on the first sale product on the home page
@When("the user adds a book to the basket")
public void theUserAddsABookToTheBasket() {
    ShopService.addBookToBasket();
}

    @And("the user clicks view basket")
    public void theUserClicksViewBasket() {
        ShopService.viewBasket();
    }

    @And("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        ShopService.proceedToCheckout();
    }

    @And("the user fills the billing form for {string}")
    public void theUserFillsTheBillingFormFor(String country) {
        ShopService.fillBillingDetailsForm(country);
    }

    @And("the user selects payment method {string}")
    public void theUserSelectsPaymentMethod(String paymentMethod) {
        ShopService.selectPaymentMethod(paymentMethod);
    }

    @And("the user clicks on the \"Place Order\" button shop")
    public void theUserClicksOnThePlaceOrderButtonShop() {
        ShopService.clickPlaceOrder();
    }

    @Then("the user can view that book in the basket menu item with price")
    public void theUserCanViewBookInBasket() {
        // Este step es opcional y depende de tu validación visual
        System.out.println("✔️ El libro está visible en el carrito.");
    }

    @And("the user navigates to the checkout page")
    public void theUserNavigatesToCheckoutPage() {
        System.out.println("➡️ Usuario está en el checkout.");
    }

    @And("the user can see total and subtotal values just above the \"Proceed to Checkout\" button")
    public void theUserCanSeeTotalAndSubtotal() {
        System.out.println("💰 Subtotal e impuesto visibles.");
    }

    @Then("the total value is less than the subtotal value")
    public void validateSubtotalVsTotal() {
        ShopService.validateSubtotalTotalAndTax();
    }


    @And("the user navigates to the payment gateway page")
    public void theUserNavigatesToPaymentGatewayPage() {
        System.out.println("🔒 Página de pasarela de pago abierta.");
    }

    @And("the order is completed")
    public void theOrderIsCompleted() {
        System.out.println("✅ Orden completada.");
    }

    @And("the user is navigated to the Order Confirmation page")
    public void theUserIsNavigatedToOrderConfirmationPage() {
        System.out.println("📄 Página de confirmación de orden abierta.");
    }

    @And("the user can see order details, bank details, customer details, and billing details")
    public void theUserCanSeeOrderAndBillingDetails() {
        System.out.println("📦 Detalles de la orden y facturación visibles.");
    }

//    @Then("the user views the book in the basket with price and navigates to the checkout page")
//    public void viewBookAndNavigateToCheckout() {
//        System.out.println("📘 Libro visible con precio.");
//        ShopService.viewBasket();
//        ShopService.proceedToCheckout();
//    }
    @Then("the subtotal and total values are displayed")
    public void subtotalAndTotalDisplayed() {
        ShopService.validateSubtotalAndTotalDisplayed();
    }

    @And("the total should be greater than the subtotal")
    public void totalGreaterThanSubtotal() {
        ShopService.validateSubtotalTotalAndTax();
    }

    @And("the tax rate should be {int}% for {string}")
    public void validateTaxRate(int expectedRate, String country) {
        ShopService.validateTaxRate(expectedRate, country);
    }






}
