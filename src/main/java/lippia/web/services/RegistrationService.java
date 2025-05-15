package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.RegistrationConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        return WebActionManager.isPresent(RegistrationConstants.SUCCESS_CONTENT);
    }


    public static boolean isOnRegistrationPage() {
        return WebActionManager.isPresent(RegistrationConstants.REGISTRATION_FORM);
    }


    public static String getErrorMessage() {
        WebElement messageElement = DriverManager.getDriverInstance().findElement(By.cssSelector(RegistrationConstants.ERROR_MESSAGE));
        return messageElement.getText();
    }


    public static boolean isRegistrationFailed() {
        return WebActionManager.isPresent(RegistrationConstants.ERROR_MESSAGE);
    }
}
