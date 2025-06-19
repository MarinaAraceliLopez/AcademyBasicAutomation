package lippia.web.steps;

import com.crowdar.driver.DriverManager;
import io.cucumber.java.en.*;
import lippia.web.services.LoginService;
import org.testng.Assert;

public class LoginSteps {


    @Then("the user should be redirected to the home page")
    public void theUserShouldBeRedirectedToTheHomePage() {
        Assert.assertTrue(LoginService.isOnHomePage(), "El usuario NO fue redirigido a la home.");
    }

    @Then("the user should see the word {string}")
    public void theUserShouldSeeTheWord(String word) {
        Assert.assertTrue(LoginService.isWordPresentOnPage(word), "Palabra no encontrada en la pantalla.");
    }

    @And("the user logs out")
    public void theUserLogsOut() {
        LoginService.performLogout();
    }

    @And("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLoginPage() {
        Assert.assertTrue(LoginService.isOnLoginPage(), "El usuario NO regresó a la pantalla de inicio de sesión.");
    }

    @And("the user presses the browser back button")
    public void userPressesBackButton() {
        DriverManager.getDriverInstance().navigate().back();
    }

    @Then("the user should not be signed in")
    public void verifyUserIsNotSignedIn() {
        Assert.assertTrue(LoginService.isOnLoginPage(), "El usuario continúa autenticado.");
    }

    @And("the user should see the login page")
    public void theUserShouldSeeTheLoginPage() {
        Assert.assertTrue(LoginService.isOnLoginPage(), "El usuario NO está en la pantalla de inicio de sesión.");
    }


}
