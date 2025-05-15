package lippia.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lippia.web.services.ShopService;

public class ShopSteps extends PageSteps {

    @When("the user adjusts the price filter between {int} and {int} rupees")
    public void adjustPriceFilter(int min, int max) {
        ShopService.setPriceRange(min, max);
    }

    @Then("the visible products are within the price range")
    public void verifyProductsInRange(int min, int max) {
        ShopService.verifyPricesInRange(min, max);
    }

    @When("the user clicks on the Filter button")
    public void theUserClicksOnTheFilterButton() {
        ShopService.clickFilterButton();
    }

    @Then("the user should see only books priced between {int} and {int} rupees")
    public void theUserShouldSeeOnlyBooksPricedBetweenAndRupees(int min, int max) {
        ShopService.verifyPricesInRange(min, max);
    }

    @When("the user selects {string} from the sorting dropdown")
    public void theUserSelectsFromTheSortingDropdown(String option) {
        ShopService.selectSortingOption(option);
    }

    @Then("the user should see the popular products")
    public void theUserShouldSeeThePopularProducts() {
        ShopService.verifyProductsVisible();
    }

    @When("the user clicks on the {string} {string} product on the home page")
    public void theUserClicksOnTheProductOnTheHomePage(String position, String type) {
        if (!type.equalsIgnoreCase("Sale")) {
            throw new IllegalArgumentException("Unsupported product type: " + type);
        }
        ShopService.clickSaleProduct(position);
    }

    @Then("the user can clearly view the actual price with the old price struck through")
    public void theUserCanClearlyViewTheActualPriceWithTheOldPriceStruckThrough() {
        ShopService.verifyPriceWithStrikeThrough();
    }
}
