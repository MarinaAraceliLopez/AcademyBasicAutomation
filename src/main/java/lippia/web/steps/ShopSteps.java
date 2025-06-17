package lippia.web.steps;

import com.crowdar.core.PageSteps;
import com.crowdar.driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import junit.framework.Assert;
import lippia.web.constants.ShopConstants;
import lippia.web.services.HomeService;
import lippia.web.services.ShopService;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static lippia.web.services.ShopService.getTaxRate;
import static org.testng.Assert.assertEquals;

// CORREGIDO: import correcto

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

    // Métodos relacionados con agregar libro al carrito y verificar
    @When("the user clicks on the {string} button for a book")
    public void theUserClicksOnAddToBasketButton(String buttonText) {
        ShopService.clickAddToBasketButton("Add To Basket");

    }

    @Then("the user can view that book in the basket menu item with price")
    public void theUserCanViewBookInBasketWithPrice() {
        ShopService.verifyBookInBasketWithPrice();
    }

    @Given("the user has added a book to the basket")
    public void theUserHasAddedABookToTheBasket() {
        ShopService.clickAddToBasketButton("Add To Basket");
    }

//    @When("the user clicks on the basket menu item link")
//    public void theUserClicksOnBasketMenuItemLink() {
//        ShopService.clickBasketMenuLink();
//    }

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

    @Given("the user is on the checkout page")
    public void theUserIsOnTheCheckoutPage() {
        ShopService.verifyOnCheckoutPage();
    }

    @When("the user clicks on the shop button {string}")
    public void theUserClicksOnButton(String buttonName) {
        // Implementación para botones de tiendas
    }


    @Then("the user navigates to the payment gateway page")
    public void theUserNavigatesToPaymentGatewayPage() {
        ShopService.verifyOnPaymentGatewayPage();
    }

    @Then("the user can view Billing Details, Order Details, Additional details, and Payment gateway details")
    public void theUserCanViewAllDetailsSections() {
        ShopService.verifyDetailsSectionsVisible();
    }

    @Given("the user is on the payment gateway page")
    public void theUserIsOnThePaymentGatewayPage() {
        ShopService.verifyOnPaymentGatewayPage();
    }

    @When("the user fills the billing details form with valid data")
    public void theUserFillsBillingDetailsForm() {
        ShopService.fillBillingDetailsForm();
    }

//    @When("the user selects payment method {string}")
//    public void theUserSelectsPaymentMethod(String paymentMethod) {
//        ShopService.selectPaymentMethod(paymentMethod);
//    }


    @When("the user clicks on the Place Order button")
    public void theUserClicksOnPlaceOrderButton() {
        ShopService.clickPlaceOrderButton();
    }

    @Then("the order is completed")
    public void theOrderIsCompleted() {
        // Validación opcional
    }

    @Then("the user is navigated to the Order Confirmation page")
    public void theUserIsNavigatedToOrderConfirmationPage() {
        ShopService.verifyOnOrderConfirmationPage();
    }

    @Then("the user can see order details, bank details, customer details, and billing details")
    public void theUserCanSeeOrderBankCustomerBillingDetails() {
        ShopService.verifyOrderDetailsSections();
    }

    @Given("the user is on the Shop page")
    public void theUserIsOnTheShopPage() {
        driver.get("http://practice.automationtesting.in/shop/");
    }

    @Then("the tax rate should be {int} percent for location {string}")
    public void theTaxRateShouldBeForLocation(int expectedTaxRate, String location) {
        ShopService.verifyTaxRateForLocation(expectedTaxRate, location);
    }


    // Puedes eliminar este método si usas el de arriba
    /*
    @Then("^the tax rate should be \"([^\"]*)\" percent for location \"([^\"]*)\"$")
    public void validateTaxRate(String expectedTaxRate, String location) {
        String actualTaxText = WebActionManager.getText(ShopConstants.TAX_LABEL);
        int actualTaxRate = Integer.parseInt(actualTaxText.replaceAll("[^0-9]", ""));
        int expectedTax = Integer.parseInt(expectedTaxRate);
        Assert.assertEquals(actualTaxRate, expectedTax, "El porcentaje del impuesto no coincide para la ubicación: " + location);
    }
    */

//    @When("the user adds a book to the basket")
//    public void theUserAddsABookToTheBasket() {
//        ShopService.clickAddToBasketButton("Add To Basket");
//    }

    @When("the user goes to the checkout page")
    public void theUserGoesToTheCheckoutPage() {
        ShopService.clickBasketMenuLink();
        ShopService.verifyOnCheckoutPage();
    }



    @Given("the user opens the browser and navigates to the site")
    public void theUserOpensTheSite() {
        WebDriver driver = DriverManager.getDriverInstance();
        driver.manage().window().maximize();
        driver.get("http://practice.automationtesting.in/");
    }

    @When("the user clicks on the Shop menu")
    public void theUserClicksOnShopMenu() {
        WebDriver driver = DriverManager.getDriverInstance();
        driver.findElement(By.linkText("Shop")).click();
    }


    @Then("the book should appear in the basket")
    public void theBookShouldAppearInBasket() {
        ShopService.verifyBookInBasketWithPrice();
    }

    @When("the user navigates to checkout")
    public void theUserNavigatesToCheckout() {
        ShopService.clickBasketMenuLink();
        ShopService.verifyOnCheckoutPage();
    }

    @Then("the total should be less than subtotal")
    public void totalShouldBeLessThanSubtotal() {
        ShopService.verifyTotalLessThanSubtotal();
    }
//    @When("the user adds a book to the basket")
//    public void theUserAddsABookToTheBasket() {
//        ShopService.addFirstBookToBasketAndProceedToCheckout();
//    }

//    @Then("the tax rate should be {double} percent for location {string}")
//    public void theTaxRateShouldBeForLocation(double expectedRate, String country) {
//        double actualRate = ShopService.getTaxRate();
//
//        assertEquals(expectedRate, actualRate, 0.01,
//                "Expected " + expectedRate + "% but got " + actualRate + "% for " + country);
//    }


    @When("the user adds a book to the basket and proceeds to checkout")
    public void theUserAddsABookToBasketAndProceedsToCheckout()
    {
        WebDriver driver = DriverManager.getDriverInstance();

        // Agregar al carrito
        By addToBasket = By.cssSelector("a.button.product_type_simple.add_to_cart_button");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToBasket));

        addBtn.click();

        // Ir al carrito
        By viewBasket = By.linkText("View Basket");

        WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(viewBasket));

        viewBtn.click();

        // Finalmente ir al checkout
        By proceedToCheckout = By.linkText("Proceed to checkout");

        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckout));

        checkoutBtn.click();

        // Asegurarte que estás en checkout
        assertTrue("No estamos en la página de checkout.",
                driver.getCurrentUrl().contains("/checkout"));
    }


    @When("the user goes to basket page")
    public void theUserGoesToBasketPage()
    {
        WebDriver driver = DriverManager.getDriverInstance();

        By basketIcon = By.xpath("//*[@id='wpmenucartli']/a/span[1]");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement basket = wait.until(ExpectedConditions.elementToBeClickable(basketIcon));

        basket.click();

        assertTrue("No estamos en la page de basket.",
                driver.getCurrentUrl().contains("/basket"));
    }

    @When("the user proceeds to checkout from basket")
    public void theUserProceedsToCheckoutFromBasket()
    {
        WebDriver driver = DriverManager.getDriverInstance();

        // Localizamos el nuevo button "Proceed to Checkout"
        By proceedBtn = By.xpath("//*[@id='page-34']/div/div[1]/div/div/div/a");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));

        proceed.click();

        // Verificamos que hemos llegado al checkout
        assertTrue("No estamos en la page de checkout.",
                driver.getCurrentUrl().contains("/checkout"));
    }





    @Then("the checkout page should show country in the order summary")
    public void theCheckoutPageShouldShowCountry()
    {
        WebDriver driver = DriverManager.getDriverInstance();

        By country = By.xpath("//*[@id='order_review']/table/tfoot/tr[2]");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement countryRow = wait.until(ExpectedConditions.visibilityOfElementLocated(country));

        assertTrue("El resumen de pedido no muestra el país.", countryRow.isDisplayed());

        // Por ejemplo, puedes imprimir o verificar que contenga "Argentina"
        // String text = countryRow.getText();
        // Assert.assertTrue(text.contains("India"), "El país no es el correcto.");
    }
    @When("the user adds a book to the basket")
    public void theUserAddsABookToTheBasket()
    {
        WebDriver driver = DriverManager.getDriverInstance();

        By addToBasket = By.cssSelector("a.button.product_type_simple.add_to_cart_button");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToBasket));

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", addBtn);
    }

    @When("the user clicks view basket")
    public void theUserClicksViewBasket()
    {
        WebDriver driver = DriverManager.getDriverInstance();

        By viewBasket = By.linkText("View Basket");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(viewBasket));

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", viewBtn);
    }

    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout()
    {
        WebDriver driver = DriverManager.getDriverInstance();

        By proceedBtn = By.xpath("//*[@id='page-34']/div/div[1]/div/div/div/a");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", proceed);
    }

    @Then("the user should be on the checkout page")
    public void theUserShouldBeOnTheCheckoutPage()
    {
        String currentURL = DriverManager.getDriverInstance().getCurrentUrl();

        assertTrue("No estamos en la página de checkout.",
                currentURL.contains("/checkout"));
    }
    @When("the user select country {string}")
    public void theUserSelectCountry(String country) {
        WebDriver driver = DriverManager.getDriverInstance();

        Select select = new Select(driver.findElement(By.id("billing_country")));
        select.selectByVisibleText(country);
    }
    @Then("the tax rate should be {double} percent for location {string}")
    public void theTaxRateShouldBeForLocation(double expectedRate, String location) {
        double actualRate = getTaxRate();
        Assert.assertEquals("La tasa de impuesto para " + location + " es " + actualRate + "% pero se esperaba " + expectedRate + "%.", expectedRate, actualRate, 0.01);

    }

    @And("the user selects payment method {string}")
    public void selectPaymentMethod(String paymentMethod) {
        WebDriver driver = DriverManager.getDriverInstance();

        String xpath = String.format(ShopConstants.PAYMENT_METHOD, paymentMethod);
        By paymentOption = By.xpath(xpath);
        if (driver.findElements(paymentOption).isEmpty()) {
            throw new AssertionError("Método de pago no encontrado: " + paymentMethod);
        }
        driver.findElement(paymentOption).click();
    }



    @And("the user clicks on the {string} button shop")
    public void clickBtn(String button) {
        WebDriver driver = DriverManager.getDriverInstance();

        if (button.equals("Place Order")) {
            driver.findElement(ShopConstants.PLACE_ORDER).click();
        } else {
            throw new IllegalArgumentException("Botón no soportado: " + button);
        }
    }

    @And("the user fills in Billing details")
    public void theUserFillsInBillingDetails() {
        HomeService.fillBillingDetails("Juan", "juan@example.com", "123123123", "Street 123.");
    }

//    @And("the user selects payment method {string}")
//    public void theUserSelectsPaymentMethod(String paymentMethod) {
//        HomeService.selectPaymentMethod(paymentMethod);
//    }

    @And("the user clicks on the Place Order button shop")
    public void theUserPlacesOrder() {
        HomeService.clickPlaceOrder();
    }

//    @Then("the order confirmation page should be visible")
//    public void theOrderConfirmationPageShouldBeVisible() {
//        assertTrue(HomeService.isOrderConfirmationPageVisible(), "Order confirmation page not visible.");
//
//    }



}
