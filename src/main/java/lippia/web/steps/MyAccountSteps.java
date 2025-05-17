package lippia.web.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import lippia.web.services.MyAccountService;

public class MyAccountSteps {

    @Then("the user should see {string}")
    public void theUserShouldSee(String expectedMessage) {
        MyAccountService.verifyWelcomeMessage(expectedMessage);
    }

    @Then("the user should see the text {string}")
    public void theUserShouldSeeText(String expectedText) {
        MyAccountService.validateTextIsPresent(expectedText);
    }

    @Then("the user should be logged out successfully")
    public void theUserShouldBeLoggedOutSuccessfully() {
        MyAccountService.verifyLogout();
    }

    @Then("the user should see the {string} section visible")
    public void theUserShouldSeeTheSectionVisible(String action) {
        MyAccountService.validateSectionIsVisible(action);
    }

    @When("the user clicks on the {string} button for the {string}")
    public void theUserClicksOnTheEditButtonFor(String button, String section) {
        if (button.equalsIgnoreCase("Edit") && section.equalsIgnoreCase("Shipping Address")) {
            MyAccountService.clickOnAccountButton("addresses");
            MyAccountService.clickOnAccountButton("edit shipping");
        }
    }

    @Then("the shipping address form should be visible")
    public void theShippingAddressFormShouldBeVisible() {
        MyAccountService.validateShippingFormIsVisible();
    }

    @And("the user has at least one order in their account")
    public void theUserHasAtLeastOneOrderInTheirAccount() {
        MyAccountService.validateOrderHistoryIsPresent();
    }

    @Then("the user should see the order detail")
    public void theUserShouldSeeTheOrderDetail() {
        MyAccountService.validateOrderDetailIsVisible();
    }

    @And("the user clicks on the {string} button of an order")
    public void theUserClicksOnTheButtonOfAnOrder(String button) {
        if (button.equalsIgnoreCase("view")) {
            MyAccountService.clickViewOrderButton();
        } else {
            throw new IllegalArgumentException("Botón '" + button + "' no reconocido en el contexto de orden.");
        }
    }

}
