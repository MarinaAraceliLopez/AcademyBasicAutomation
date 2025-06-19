package lippia.web.services;

import com.crowdar.driver.DriverManager;
import lippia.web.constants.HomePageConstants;
import lippia.web.constants.LoginConstants;
import lippia.web.constants.SuperiorNavigationBarConstants;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonService {

    public static void clickOnMenu(String menuName) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriverInstance(), 10);

        if (menuName.equalsIgnoreCase("My Account")) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    LocatorService.getBy(SuperiorNavigationBarConstants.MENU_MY_ACCOUNT)));
            DriverManager.getDriverInstance().findElement(
                    LocatorService.getBy(SuperiorNavigationBarConstants.MENU_MY_ACCOUNT)).click();
        } else if (menuName.equalsIgnoreCase("Shop")) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    LocatorService.getBy(HomePageConstants.BUTTON_SHOP_XPATH)));
            DriverManager.getDriverInstance().findElement(
                    LocatorService.getBy(HomePageConstants.BUTTON_SHOP_XPATH)).click();
        } else {
            throw new UnsupportedOperationException("La opción de menú '" + menuName + "' no está implementada.");
        }
    }

    public static String getElementByName(String elementName) {
        switch (elementName.toLowerCase()) {
            case "login email":
                return LoginConstants.USERNAME_EMAIL_FIELD;
            case "login password":
                return LoginConstants.LOGIN_PASSWORD_FIELD;
            case "login":
                return LoginConstants.LOGIN_BUTTON;
            case "logout":
                return LoginConstants.LOGOUT_BUTTON;
            case "my account":
                return LoginConstants.MY_ACCOUNT_MENU;
            default:
                throw new IllegalArgumentException("No se encontró el locator para: " + elementName);
        }
    }
}

