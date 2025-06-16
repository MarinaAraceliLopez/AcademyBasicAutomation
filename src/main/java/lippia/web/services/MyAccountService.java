package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.MyAccountConstants;

import org.openqa.selenium.By;
import org.testng.Assert;

public class MyAccountService {

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
            return By.xpath(locator); // Por defecto XPath
        }
    }


    public static void verifyWelcomeMessage(String expectedMessage) {
        String actualMessage = WebActionManager.getText(MyAccountConstants.WELCOME_MESSAGE);
        Assert.assertTrue(actualMessage != null && actualMessage.contains(expectedMessage),
                "Expected: '" + expectedMessage + "' but found: '" + actualMessage + "'");
    }

    public static void clickOnAccountButton(String button) {
        String locator;
        switch (button.toLowerCase()) {
            case "logout":
                locator = MyAccountConstants.LOGOUT_BUTTON;
                break;
            case "account details":
                locator = MyAccountConstants.ACCOUNT_DETAILS_BUTTON;
                break;
            case "addresses":
                locator = MyAccountConstants.ADDRESS_BUTTON;
                break;
            case "orders":
                locator = MyAccountConstants.ORDERS_BUTTON;
                break;
            case "edit shipping":
                locator = MyAccountConstants.EDIT_SHIPPING_ADDRESS_BUTTON;
                break;
            case "login":
                locator = "xpath://input[@name='login']";
                break;
            default:
                throw new IllegalArgumentException("El botón '" + button + "' no está definido.");
        }
        WebActionManager.click(locator);
    }

//    public static void enterValueInField(String value, String fieldName) {
//        String locator;
//        switch (fieldName.toLowerCase()) {
//            case "login email":
//                locator = MyAccountConstants.LOGIN_EMAIL_FIELD;
//                break;
//            case "login password":
//                locator = MyAccountConstants.LOGIN_PASSWORD_FIELD;
//                break;
//            case "current password":
//                locator = MyAccountConstants.CURRENT_PASSWORD_FIELD;
//                break;
//            case "new password":
//                locator = MyAccountConstants.NEW_PASSWORD_FIELD;
//                break;
//            case "confirm password":
//                locator = MyAccountConstants.CONFIRM_PASSWORD_FIELD;
//                break;
//            default:
//                throw new IllegalArgumentException("El campo '" + fieldName + "' no está definido.");
//        }
//        WebActionManager.setInput(locator, value);
//    }

    public static void validateTextIsPresent(String expectedText) {
        String locator = "xpath://*[contains(text(),'" + expectedText + "')]";
        Assert.assertTrue(WebActionManager.isVisible(locator),
                "El texto esperado '" + expectedText + "' no está visible en la página.");
    }

//    public static void validateMessage(String expectedText) {
//        String locator = MyAccountConstants.RESULT_MESSAGE;
//        Assert.assertTrue(WebActionManager.isVisible(locator), "No se encontró el mensaje para validar.");
//        String actualText = WebActionManager.getText(locator);
//        Assert.assertTrue(actualText.contains(expectedText),
//                "Mensaje esperado: '" + expectedText + "', pero se encontró: '" + actualText + "'");
//    }

    public static void validateShippingFormIsVisible() {
        String locator = "xpath://form[contains(@action,'edit-address/shipping')]";
        Assert.assertTrue(WebActionManager.isVisible(locator),
                "El formulario de dirección de envío no es visible.");
    }

    public static void validateOrderHistoryIsPresent() {
        String locator = "xpath://section[contains(@class,'woocommerce-orders')]";
        Assert.assertTrue(WebActionManager.isVisible(locator),
                "No se encontró ninguna orden en la cuenta del usuario.");
    }

    public static void validateOrderDetailIsVisible() {
        String locator = "xpath://div[contains(@class,'woocommerce-order')]";
        Assert.assertTrue(WebActionManager.isVisible(locator),
                "Los detalles del pedido no están visibles.");
    }

    public static void clickViewOrderButton() {
        String locator = "xpath://a[contains(text(),'View')]";
        WebActionManager.click(locator);
    }

    public static void validateSectionIsVisible(String action) {
        String locator;
        switch (action.toLowerCase()) {
            case "account details":
                locator = "xpath://div[contains(@class,'woocommerce-MyAccount-content')]//h2[contains(text(),'Account Details')]";
                break;
            case "addresses":
                locator = "xpath://h3[contains(text(),'Billing Address') or contains(text(),'Shipping Address')]";
                break;
            case "orders":
                locator = "xpath://div[contains(@class,'woocommerce-MyAccount-content')]//h2[contains(text(),'Orders')] | //div[contains(text(),'No order has been made yet')]";
                break;
            default:
                throw new IllegalArgumentException("La acción '" + action + "' no está definida para validar.");
        }
        Assert.assertTrue(WebActionManager.isVisible(locator),
                "No se encontró el contenido esperado para la acción '" + action + "'");
    }

    public static void verifyLogout() {
        Assert.assertTrue(WebActionManager.isVisible(MyAccountConstants.LOGIN_FORM),
                "El formulario de login no es visible, posible error al cerrar sesión.");
    }

    public static void editPassword(String currentPassword, String newPassword, String confirmPassword) {
        if (canChangePassword()) {
            WebActionManager.setInput(MyAccountConstants.CURRENT_PASSWORD_FIELD, currentPassword);
            WebActionManager.setInput(MyAccountConstants.NEW_PASSWORD_FIELD, newPassword);
            WebActionManager.setInput(MyAccountConstants.CONFIRM_PASSWORD_FIELD, confirmPassword);
            WebActionManager.click(MyAccountConstants.SAVE_CHANGES_BUTTON);
        } else {
            throw new RuntimeException("No están presentes los campos necesarios para cambiar la contraseña.");
        }
    }

    public static void validatePasswordChangeConfirmation(String expectedMessage) {
        String locator = MyAccountConstants.RESULT_MESSAGE;
        Assert.assertTrue(WebActionManager.isVisible(locator),
                "No se encontró el mensaje de confirmación de cambio de contraseña.");
        String actual = WebActionManager.getText(locator);
        Assert.assertTrue(actual.contains(expectedMessage),
                "Mensaje de confirmación esperado: '" + expectedMessage + "', pero se encontró: '" + actual + "'");
    }

    public static void validateAccountDetailsForm() {
        Assert.assertTrue(WebActionManager.isVisible(MyAccountConstants.SAVE_CHANGES_BUTTON),
                "El formulario de detalles de cuenta no es visible.");
    }

//    public static void clickOnSectionButton(String section) {
//        String locator;
//        switch (section.toLowerCase()) {
//            case "account details":
//                locator = MyAccountConstants.ACCOUNT_DETAILS_BUTTON;
//                break;
//            case "addresses":
//                locator = MyAccountConstants.ADDRESS_BUTTON;
//                break;
//            case "orders":
//                locator = MyAccountConstants.ORDERS_BUTTON;
//                break;
//            default:
//                throw new IllegalArgumentException("La sección '" + section + "' no está definida para hacer click.");
//        }
//        WebActionManager.click(locator);
//    }

//    public static void clickOnAccountLink() {
//        WebActionManager.click(MyAccountConstants.MY_ACCOUNT_LINK);
//    }

    public static boolean canChangePassword() {
        return WebActionManager.isPresent(MyAccountConstants.CURRENT_PASSWORD_FIELD)
                && WebActionManager.isPresent(MyAccountConstants.NEW_PASSWORD_FIELD)
                && WebActionManager.isPresent(MyAccountConstants.CONFIRM_PASSWORD_FIELD);
    }


}
