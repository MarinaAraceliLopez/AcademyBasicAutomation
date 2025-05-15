

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lippia.web.services.MyAccountService;


public class MyAccountSteps  {
    @Then("the user should see {string}")
    public void theUserShouldSee(String message) {
        if (message.equals("Welcome to your account dashboard")) {
            MyAccountService.verifyWelcomeMessage(message);
        } else {
            MyAccountService.validateResultMessage(message);
        }
    }

    @When("the user clicks on the {string} account button")
    public void theUserClicksOnTheAccountButton(String button) {
        MyAccountService.clickOnAccountButton(button);
    }

    @When("the user clicks on the {string} button for the {string}")
    public void theUserClicksOnTheButtonFor(String button, String section) {
        MyAccountService.clickButtonForSection(button, section);
    }

    @When("the user clicks edit shipping address")
    public void theUserClicksEditShippingAddress() {
        MyAccountService.clickEditShippingAddress();
    }

    @Then("the user can edit shipping address")
    public void verifyEditShippingAddressVisible() {
        MyAccountService.verifyShippingAddressFormIsVisible();
    }



}
