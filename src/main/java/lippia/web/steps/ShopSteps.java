package lippia.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lippia.web.services.ShopService;

public class ShopSteps extends PageSteps {

    @When("the user adjusts the price filter between {int} and {int} rupees")
    public void adjustPriceFilter(int min, int max) {
        int priceMin = 150;
        int priceMax = 500;

        ShopService.setPriceRangeByValues(min, max, priceMin, priceMax);
        ShopService.clickFilterButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("the user clicks on the Filter button")
    public void theUserClicksOnTheFilterButton() {
        ShopService.clickFilterButton();
    }

    @Then("the user should see only books priced between {int} and {int} rupees")
    public void verifyBooksPrices(int min, int max) {
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
