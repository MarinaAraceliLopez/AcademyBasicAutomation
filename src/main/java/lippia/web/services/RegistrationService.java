package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.RegistrationConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationService {

    public static void fillRegistrationField(String field, String value) {
        switch (field) {
            case "registration email":
                WebActionManager.setInput(RegistrationConstants.REGISTRATION_EMAIL, value);
                break;
            case "registration password":
                WebActionManager.setInput(RegistrationConstants.REGISTRATION_PASSWORD, value);
                break;
            default:
                throw new IllegalArgumentException("Campo desconocido: " + field);
        }
    }

    public static void clickButton(String button) {
        switch (button) {
            case "register":
                WebActionManager.click(RegistrationConstants.REGISTER_BUTTON);
                break;
            default:
                throw new IllegalArgumentException("Botón desconocido: " + button);
        }
    }

    public static boolean isRegistrationSuccessful() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriverInstance(), 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(byFromString(RegistrationConstants.SUCCESS_CONTENT)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public static boolean isOnRegistrationPage() {
        return WebActionManager.isPresent(RegistrationConstants.REGISTRATION_FORM);
    }

    public static String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriverInstance(), 10);
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(byFromString(RegistrationConstants.ERROR_MESSAGE)));
        return message.getText();
    }

//    public static boolean isRegistrationFailed() {
//        return WebActionManager.isPresent(RegistrationConstants.ERROR_MESSAGE);
//    }

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
            return By.xpath(locator);
        }
    }




}
