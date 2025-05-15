package lippia.web.steps;

import io.cucumber.java.en.*;
import lippia.web.services.RegistrationService;
import org.testng.Assert;

public class RegistrationSteps {

    @Then("the user should see a registration confirmation or welcome message")
    public void theUserShouldSeeAConfirmationMessage() {
        Assert.assertTrue(RegistrationService.isRegistrationSuccessful(), "No se mostró el mensaje de bienvenida.");
    }
}
