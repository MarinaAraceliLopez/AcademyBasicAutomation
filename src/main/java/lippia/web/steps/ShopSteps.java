package lippia.web.steps;

import com.crowdar.core.PageSteps;
import com.crowdar.core.actions.WebActionManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lippia.web.constants.ShopConstants;
import lippia.web.services.ShopService;
import org.testng.Assert;

public class ShopSteps extends PageSteps {

    @When("the user adjusts the price filter between {int} and {int} rupees")
    public void adjustPriceFilter(int min, int max) {
        int priceMin = 150;
        int priceMax = 500;

        ShopService.setPriceRangeByValues(min, max, priceMin, priceMax);
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


    @When("the user selects {string} from the sorting dropdown")
    public void theUserSelectsFromTheSortingDropdown(String option) {
        ShopService.selectSortingOption(option);
    }

    @Then("the user should see the popular products")
    public void theUserShouldSeeThePopularProducts() {
        ShopService.verifyProductsVisible();
    }

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



        @When("the user clicks on the {string} button for a book")
        public void theUserClicksOnAddToBasketButton(String buttonText) {
            ShopService.clickAddToBasketButton(buttonText);
        }

        @Then("the user can view that book in the basket menu item with price")
        public void theUserCanViewBookInBasketWithPrice() {
            ShopService.verifyBookInBasketWithPrice();
        }

        @When("the user clicks on the basket menu item link")
        public void theUserClicksOnBasketMenuItemLink() {
            ShopService.clickBasketMenuLink();
        }

        @Then("the user navigates to the checkout page")
        public void theUserNavigatesToCheckoutPage() {
            ShopService.verifyOnCheckoutPage();
        }

        @Then("the user can see total and subtotal values just above the {string} button")
        public void theUserCanSeeTotalAndSubtotalAboveButton(String buttonText) {
            ShopService.verifyTotalAndSubtotalVisible(buttonText);
        }

        @Then("the total value is less than the subtotal value")
        public void theTotalValueIsLessThanSubtotalValue() {
            ShopService.verifyTotalLessThanSubtotal();
        }

//        @When("the user clicks on the {string} button")
//        public void theUserClicksOnButton(String buttonText) {
//            ShopService.clickButtonByText(buttonText);
//        }

        @Then("the user navigates to the payment gateway page")
        public void theUserNavigatesToPaymentGatewayPage() {
            ShopService.verifyOnPaymentGatewayPage();
        }

        @Then("the user can view Billing Details, Order Details, Additional details, and Payment gateway details")
        public void theUserCanViewAllDetailsSections() {
            ShopService.verifyDetailsSectionsVisible();
        }

        @When("the user fills the billing details form with valid data")
        public void theUserFillsBillingDetailsForm() {
            ShopService.fillBillingDetailsForm();
        }

        @When("the user selects payment method {string}")
        public void theUserSelectsPaymentMethod(String paymentMethod) {
            ShopService.selectPaymentMethod(paymentMethod);
        }

        @When("the user clicks on the Place Order button")
        public void theUserClicksOnPlaceOrderButton() {
            ShopService.clickPlaceOrderButton();
        }

        @Then("the order is completed and the user is navigated to the Order Confirmation page")
        public void theOrderIsCompletedAndUserIsNavigatedToConfirmation() {
            ShopService.verifyOnOrderConfirmationPage();
        }

        @Then("the user can see order details, bank details, customer details, and billing details")
        public void theUserCanSeeOrderBankCustomerBillingDetails() {
            ShopService.verifyOrderDetailsSections();
        }

        @Then("the tax rate should be {int} percent for location {string}")
        public void theTaxRateShouldBeForLocation(int expectedTaxRate, String location) {
            ShopService.verifyTaxRateForLocation(expectedTaxRate, location);
        }

    @When("the user adds a book to the basket")
    public void theUserAddsABookToTheBasket() {
        ShopService.clickAddToBasketButton("Add To Basket");
    }

    @When("the user goes to the checkout page")
    public void theUserGoesToTheCheckoutPage() {
        ShopService.clickBasketMenuLink();
        ShopService.verifyOnCheckoutPage();
    }

    @Then("^the tax rate should be \"([^\"]*)\" percent for location \"([^\"]*)\"$")
    public void validateTaxRate(String expectedTaxRate, String location) {
        // Obtener el texto del impuesto desde la página
        String actualTaxText = WebActionManager.getText(ShopConstants.TAX_LABEL);

        // Extraer el porcentaje numérico del texto (por ejemplo, "2%" -> 2)
        int actualTaxRate = Integer.parseInt(actualTaxText.replaceAll("[^0-9]", ""));

        // Convertir el valor esperado a entero
        int expectedTax = Integer.parseInt(expectedTaxRate);

        // Validar el porcentaje
        Assert.assertEquals(
                actualTaxRate,
                expectedTax,
                "El porcentaje del impuesto no coincide para la ubicación: " + location
        );
    }
    }



