package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.HomePageConstants;
import lippia.web.constants.SuperiorNavigationBarConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonService {

    public static void clickOnMenu(String menuName) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriverInstance(), 10);

        if (menuName.equalsIgnoreCase("My Account")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul//li//a[contains(text(),'My Account')]")));
            WebActionManager.click(SuperiorNavigationBarConstants.MENU_MY_ACCOUNT);
        } else if (menuName.equalsIgnoreCase("Shop")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menu-item-40']/a")));
            WebActionManager.click(HomePageConstants.BUTTON_SHOP_XPATH);
        } else {
            throw new UnsupportedOperationException("La opción de menú '" + menuName + "' no está implementada.");
        }

    }
}
