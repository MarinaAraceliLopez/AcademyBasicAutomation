package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.LoginConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginService {


    public static void fillLoginField(String field, String value) {
        switch (field) {
            case "login email":
                WebActionManager.setInput(LoginConstants.USERNAME_EMAIL_FIELD, value);
                break;
            case "login password":
                WebActionManager.setInput(LoginConstants.LOGIN_PASSWORD_FIELD, value);
                break;
            default:
                throw new IllegalArgumentException("Campo desconocido: " + field);
        }
    }


    public static void clickLoginButton() {
        WebActionManager.click(LoginConstants.LOGIN_BUTTON);
    }


    public static boolean isLoginSuccessful() {
        return WebActionManager.isPresent(LoginConstants.SUCCESS_CONTENT);
    }


    public static boolean isOnLoginPage() {
        return WebActionManager.isPresent(LoginConstants.MY_ACCOUNT_MENU);
    }


    public static String getErrorMessage() {
        WebElement messageElement = DriverManager.getDriverInstance()
                .findElement(By.xpath(LoginConstants.ERROR_MESSAGE.replace("xpath:", "")));
        return messageElement.getText();
    }



    public static boolean isLoginFailed() {
        return WebActionManager.isPresent(LoginConstants.ERROR_MESSAGE);
    }
}
