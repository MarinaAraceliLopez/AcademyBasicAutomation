package lippia.web.steps;

import io.cucumber.java.en.*;
import lippia.web.services.FormService;

public class FormSteps {

    @When("the user enters {string} in the {string} field")
    public void theUserEntersInTheField(String value, String field) {
        FormService.fillField(value, field);
    }

    @When("the user clicks on the {string} button")
    public void theUserClicksOnTheButton(String button) {
        FormService.clickButton(button);
    }

    @Then("a message saying {string} should be displayed")
    public void aMessageSayingShouldBeDisplayed(String expectedMessage) {
        FormService.validateDisplayedMessage(expectedMessage);
    }

    @Then("the user should stay on the same page")
    public void theUserShouldStayOnTheSamePage() {
        FormService.verifyUserStaysOnSamePage();
    }
}
