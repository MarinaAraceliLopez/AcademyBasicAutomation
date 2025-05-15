package lippia.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.*;
import lippia.web.services.LoginService;
import org.testng.Assert;

public class LoginSteps extends PageSteps {

    @Then("the user should be redirected to the home page")
    public void theUserShouldBeRedirectedToHomePage() {
        Assert.assertTrue(LoginService.isLoginSuccessful(), "El usuario no fue redirigido correctamente.");
    }
}
