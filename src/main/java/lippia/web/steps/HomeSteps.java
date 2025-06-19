package lippia.web.steps;
import com.crowdar.driver.DriverManager;
import io.cucumber.java.en.*;
import lippia.web.services.HomeService;
import lippia.web.services.ShopService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomeSteps  {

// 1,2 Scenario: Home page shows 3 Arrivals
    @Then("the Home page shows only 3 Arrivals")
    public void theHomePageShowsOnly3Arrivals() {
    HomeService.validateArrivalsCountIsThree();
    }

    @Then("the Home page has 3 Sliders only")
    public void theHomePageHas3SlidersOnly() {
        HomeService.validateSlidersCountIsThree();
    }

    @And("the Home page must contain only 3 Sliders")
    public void theHomePageMustContainOnly3Sliders() {
        HomeService.validateSlidersCountIsThree(); // Reutilizamos el mismo método
    }



// 4,5 Scenario: Complete the order process and verify order confirmation page
    @When("the user clicks on the image of any Arrival")
    public void clickOnArrivalImage() {
        HomeService.clickAnyArrivalImage();
    }

    @And("the book detail page is displayed with Add to Basket button")
    public void validateBookDetailPage() {
        HomeService.validateBookDetailPageHasAddToBasket();
    }

    @And("the user adds a book to the basket home")
    public void theUserAddsABookToTheBasket() {
        HomeService.addProductFromHome();
    }

    @When("the user adds a book from the home page")
    public void theUserAddsABookFromHome() {
        HomeService.addProductFromHome();
    }

    @And("the user clicks add basket home")
    public void theUserClicksAddBasketHome() {
        HomeService.addProductFromHome();
    }
    @And("the user clicks view basket home")
    public void theUserClicksViewBasketHome() {
        HomeService.viewBasketFromHome();
    }

    @And("the user proceeds to checkout home")
    public void theUserProceedsToCheckoutHome() {
        HomeService.checkBasketHome();
    }
    @And("the user autocompletes the billing form with country {string} and state {string}")
    public void userFillsBillingForm(String country, String state) {
        WebDriver driver = DriverManager.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_first_name"))).sendKeys("Juan");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_last_name"))).sendKeys("Pérez");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_email"))).sendKeys("juan.perez@example.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_phone"))).sendKeys("1234567890");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_address_1"))).sendKeys("Calle Falsa 123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_city"))).sendKeys("Ciudad");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_postcode"))).sendKeys("12345");

        Select countryDropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("billing_country"))));
        countryDropdown.selectByVisibleText(country);

        Select stateDropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.id("billing_state"))));
        stateDropdown.selectByVisibleText(state);

        System.out.println("✅ Formulario completado correctamente con país " + country + " y estado " + state);
    }


    @And("the user selects payment method {string} home")
    public void theUserSelectsPaymentMethod(String method) {
        ShopService.selectPaymentMethod(method);
    }

    @And("the user clicks on \"Place Order\" button home")
    public void theUserClicksPlaceOrder() {
        ShopService.clickPlaceOrder();
    }

    @Then("the order is completed home")
    public void theOrderIsCompleted() {
        ShopService.validateOrderCompletion();
    }

    @And("the order confirmation page shows order, bank, customer, and billing details")
    public void theUserCanSeeOrderAndBillingDetails() {
        ShopService.validateOrderConfirmationDetails();
    }

    @Then("the total should be less than the subtotal")
    public void theTotalIsLessThanSubtotalHome() {
        ShopService.validateSubtotalTotalAndTax(); // o similar
    }

    @And("the payment page should show Billing Details, Order Details, Additional Details, and Payment gateway options")
    public void paymentPageShowsAllBlocks() {
        ShopService.validatePaymentPageSectionsPresent();
    }















}
