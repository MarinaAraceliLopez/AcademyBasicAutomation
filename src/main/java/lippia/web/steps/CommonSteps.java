package lippia.web.steps;

import com.crowdar.core.PageSteps;
import com.crowdar.core.actions.WebActionManager;
import io.cucumber.java.en.*;
import lippia.web.services.CommonService;

public class CommonSteps extends PageSteps {

    @Given("the user opens the browser")
    public void theUserOpensTheBrowser() {
        System.out.println("Browser is ready");
    }

    @Given("the user navigates to the URL {string}")
    public void theUserNavigatesToTheURL(String url) {
        WebActionManager.navigateTo(url);
    }

    @When("the user clicks on the {string} menu")
    public void theUserClicksOnTheMenu(String menuName) {
        CommonService.clickOnMenu(menuName);
    }
}

