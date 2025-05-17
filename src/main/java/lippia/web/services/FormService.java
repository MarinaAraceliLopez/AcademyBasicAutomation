package lippia.web.services;

import org.testng.Assert;

public class FormService {

    public static void fillField(String value, String field) {
        String lowerField = field.toLowerCase();

        if (lowerField.contains("registration")) {
            RegistrationService.fillRegistrationField(lowerField, value);
        } else if (lowerField.contains("login")) {
            LoginService.fillLoginField(lowerField, value);
        } else {
            throw new IllegalArgumentException("Campo no reconocido: " + field);
        }
    }

    public static void clickButton(String button) {
        switch (button.toLowerCase()) {
            case "register":
                RegistrationService.clickButton("register");
                break;
            case "login":
                LoginService.clickLoginButton();
                break;
            case "logout":
            case "account details":
            case "addresses":
            case "orders":
            case "edit shipping":
                MyAccountService.clickOnAccountButton(button);
                break;
            default:
                throw new IllegalArgumentException("Botón no soportado: " + button);
        }
    }

    public static void validateDisplayedMessage(String expectedMessage) {
        String actualMessage;

        if (LoginService.isLoginFailed()) {
            actualMessage = LoginService.getErrorMessage();
        } else {
            actualMessage = RegistrationService.getErrorMessage();
        }

        Assert.assertTrue(actualMessage.toLowerCase().contains(expectedMessage.toLowerCase()),
                "Mensaje esperado: '" + expectedMessage + "' pero se encontró: '" + actualMessage + "'");
    }

    public static void verifyUserStaysOnSamePage() {
        boolean onLoginPage = LoginService.isOnLoginPage();
        boolean onRegistrationPage = RegistrationService.isOnRegistrationPage();

        Assert.assertTrue(onLoginPage || onRegistrationPage,
                "El usuario no se mantuvo en la misma página (ni login ni registro).");
    }
}
