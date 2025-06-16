package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.LoginConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginService {

    private static By byFromString(String locator) {
        if (locator.startsWith("xpath:")) {
            return By.xpath(locator.replace("xpath:", ""));
        } else if (locator.startsWith("css:")) {
            return By.cssSelector(locator.replace("css:", ""));
        } else if (locator.startsWith("id:")) {
            return By.id(locator.replace("id:", ""));
        } else if (locator.startsWith("name:")) {
            return By.name(locator.replace("name:", ""));
        } else {
            // Si no tiene prefijo, asumimos que es un XPath
            return By.xpath(locator);
        }
    }

    public static void fillLoginField(String field, String value) {
        if (field.equals("login email")) {
            WebActionManager.setInput(LoginConstants.USERNAME_EMAIL_FIELD, value);
        } else if (field.equals("login password")) {
            WebActionManager.setInput(LoginConstants.LOGIN_PASSWORD_FIELD, value);
        } else {
            throw new IllegalArgumentException("Campo desconocido: " + field);
        }
    }

    public static void clickLoginButton() {
        DriverManager.getDriverInstance()
                .findElement(LocatorService.getBy(LoginConstants.LOGIN_BUTTON))
                .click();
    }

    public static boolean isLoginFailed() {
        return isElementPresent(byFromString(LoginConstants.ERROR_MESSAGE));
    }

    public static String getErrorMessage() {
        return DriverManager.getDriverInstance()
                .findElement(byFromString(LoginConstants.ERROR_MESSAGE))
                .getText();
    }

    public static boolean isOnLoginPage() {
        return isElementPresent(byFromString(LoginConstants.USERNAME_EMAIL_FIELD));
    }

    public static boolean isOnHomePage() {
        return isElementPresent(byFromString(LoginConstants.LOGOUT_BUTTON));
    }

    public static void performLogout() {
        DriverManager.getDriverInstance()
                .findElement(byFromString(LoginConstants.LOGOUT_BUTTON))
                .click();
    }

    private static boolean isElementPresent(By by) {
        WebDriver driver = DriverManager.getDriverInstance();

        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isWordPresentOnPage(String word) {
        WebDriver driver = DriverManager.getDriverInstance();
        return driver.getPageSource().contains(word);
    }
}
