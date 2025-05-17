package lippia.web.services;

import com.crowdar.core.PropertyManager;
import lippia.web.constants.HomePageConstants;
import org.testng.Assert;

import static com.crowdar.core.actions.ActionManager.click;
import static com.crowdar.core.actions.ActionManager.isPresent;
import static com.crowdar.core.actions.WebActionManager.navigateTo;

public class PracticeHomeService {

    public static void navegarWeb(){
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void clickShopButton() {
        click(HomePageConstants.BUTTON_SHOP_XPATH);
    }

    public static void verificarShopPage() {
        Assert.assertTrue(isPresent(HomePageConstants.ORDERBY_XPATH));
    }
}
