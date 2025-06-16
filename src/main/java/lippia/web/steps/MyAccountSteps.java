package lippia.web.steps;

import com.crowdar.core.actions.WebActionManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lippia.web.constants.MyAccountConstants;
import lippia.web.services.MyAccountService;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @When("the user edits their password")
    public void theUserEditsTheirPassword() {
        String currentPassword = "Marina_12_26";
        String newPassword = "NewPassword123!";
        String confirmPassword = "NewPassword123!";
        MyAccountService.editPassword(currentPassword, newPassword, confirmPassword);
    }

    @Then("the user should see a confirmation message")
    public void theUserShouldSeeAConfirmationMessage() {
        String expectedMessage = "Account details changed successfully.";
        MyAccountService.validatePasswordChangeConfirmation(expectedMessage);
    }

    @Then("the user should see the account details section")
    public void theUserShouldSeeTheAccountDetailsSection() {
        MyAccountService.validateAccountDetailsForm();
    }
    @Then("the user should be able to change the password")
    public void theUserShouldBeAbleToChangeThePassword() {
        boolean canChange = MyAccountService.canChangePassword();
        if (!canChange) {
            throw new AssertionError("Password change fields are not visible");
        }
        WebActionManager.setInput(MyAccountConstants.FIRST_NAME_FIELD, "Marina");
        WebActionManager.setInput(MyAccountConstants.LAST_NAME_FIELD, "Lopez");

        WebActionManager.setInput(MyAccountConstants.CURRENT_PASSWORD_FIELD, "Marina_12_26");
        WebActionManager.setInput(MyAccountConstants.NEW_PASSWORD_FIELD, "NuevaPassword123!");
        WebActionManager.setInput(MyAccountConstants.CONFIRM_PASSWORD_FIELD, "NuevaPassword123!");


        // Esperar a que el botón esté visible antes de hacer click

        WebActionManager.click(MyAccountConstants.SAVE_CHANGES_BUTTON);
    }
    @And("the user changes password from {string} to {string}")
    public void theUserChangesPasswordFromTo(String oldPassword, String newPassword) {
        MyAccountService.editPassword(oldPassword, newPassword, newPassword);
    }


    @Then("the password should be successfully updated")
    public void thePasswordShouldBeSuccessfullyUpdated() {
        MyAccountService.validatePasswordChangeConfirmation("Account details changed successfully.");
    }


}
