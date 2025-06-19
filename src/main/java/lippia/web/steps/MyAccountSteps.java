package lippia.web.steps;

import io.cucumber.java.en.*;
import lippia.web.services.MyAccountService;
import static org.testng.Assert.assertTrue;

public class MyAccountSteps {

    @When("the user clicks on the {string} button in account")
    public void theUserClicksOnTheButtonInAccount(String button) {
        MyAccountService.clickMyAccountOption(button);
    }

    @And("the user clicks on the {string} button for the {string}")
    public void theUserClicksEditFor(String button, String context) {
        MyAccountService.handleAccountButtonClick(button, context);
    }


    @Then("the user should see the {string} section visible")
    public void theUserShouldSeeTheSectionVisible(String action) {
        boolean isVisible = MyAccountService.isSectionVisible(action);
        assertTrue(isVisible, "The " + action + " section is NOT visible.");
    }

    @Then("the shipping address form should be visible")
    public void theShippingAddressFormShouldBeVisible(){
        assertTrue(MyAccountService.isShippingFormVisible(), "Shipping form not visible.");
    }

    @And("the user clicks on the \"View\" button of an order")
    public void theUserClicksViewOfAnOrder() {
        MyAccountService.viewOrderDetails();
    }

    @And("the user clicks on the Logout button")
    public void theUserClicksLogout(){
        MyAccountService.performLogout();
    }

    @Then("the user should be logged out successfully")
    public void theUserShouldBeLoggedOut(){
        assertTrue(MyAccountService.isLoggedOut(), "The user is not logged out.");
    }

    @And("the user should see {string}")
    public void theUserShouldSeeTextOnPage(String text) {
        boolean isVisible = MyAccountService.isWordPresentOnPage(text);
        assertTrue(isVisible, "Text " + text + " not visible on page.");
    }

    @Given("the user has at least one order in their account")
    public void theUserHasAtLeastOneOrderInAccount() {
        assertTrue(MyAccountService.hasAnyOrder(), "No orders were found in the account.");
    }

    @Then("the user should see the order detail")
    public void theUserShouldSeeTheOrderDetails(){
        assertTrue(MyAccountService.isOrderDetailsVisible(), "The order details are NOT visible.");
    }

    @Then("the user should have products in the cart")
    public void theUserHasProductsInCart(){
        assertTrue(MyAccountService.hasAnyProductInCart(), "The cart is empty.");
    }

    @Then("the user should view order details")
    public void viewOrderDetailsStep() {
        MyAccountService.validateAccountGreeting();
    }


    @Then("the cart should already have products")
    public void theCartShouldHaveProducts(){
        assertTrue(MyAccountService.hasProductInCart(), "El carrito está vacío.");
    }

    @And("the user changes password from {string} to {string}")
    public void theUserChangesPasswordFromTo(String currentPass, String newPass) {
        MyAccountService.changePassword(currentPass, newPass);
    }

    @Then("the password should be successfully updated")
    public void passwordShouldBeSuccessfullyUpdated(){
        assertTrue(MyAccountService.isChangeSuccessMessageVisible(), "El password NO se actualizó.");
    }

    @And("the user enters first name {string}, last name {string} and email {string}")
    public void theUserEntersAccountDetails(String firstName, String lastName, String email) {
        MyAccountService.fillAccountDetails(firstName, lastName, email);
    }

    @And("the user enters current password {string} and new password {string}")
    public void theUserEntersPasswords(String currentPass, String newPass) {
        MyAccountService.fillPasswords(currentPass, newPass);
    }

    @And("the user saves the account details")
    public void theUserSavesAccountDetails() {
        MyAccountService.saveAccountDetails();
    }

    @Then("the account details should be successfully updated")
    public void accountDetailsShouldBeUpdated() {
        assertTrue(MyAccountService.isChangeSuccessMessageVisible(), "Los datos NO se actualizaron.");
    }


}
