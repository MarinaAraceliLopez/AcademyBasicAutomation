package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.MyAccountConstants;
import org.testng.Assert;

public class MyAccountService {

    public static void verifyWelcomeMessage(String expectedMessage) {
        String actualMessage = WebActionManager.getText(MyAccountConstants.WELCOME_MESSAGE);
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected: '" + expectedMessage + "' but found: '" + actualMessage + "'");
    }

    public static void clickOnAccountButton(String button) {
        switch (button.toLowerCase()) {
            case "logout":
                WebActionManager.click(MyAccountConstants.LOGOUT_BUTTON);
                break;
            case "account details":
                WebActionManager.click(MyAccountConstants.ACCOUNT_DETAILS_BUTTON);
                break;
            case "address":
                WebActionManager.click(MyAccountConstants.ADDRESS_BUTTON);
                break;
            case "orders":
                WebActionManager.click(MyAccountConstants.ORDERS_BUTTON);
                break;
            default:
                throw new IllegalArgumentException("Botón no reconocido: " + button);
        }
    }

    public static void validateResultMessage(String expectedMessage) {
        String actualMessage = WebActionManager.getText(MyAccountConstants.RESULT_MESSAGE);
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected message: '" + expectedMessage + "', but got: '" + actualMessage + "'");
    }

    public static void clickEditShippingAddress() {
        WebActionManager.click(MyAccountConstants.EDIT_SHIPPING_ADDRESS_BUTTON);
    }

    public static void verifyShippingAddressFormIsVisible() {
        boolean isVisible = WebActionManager.isPresent(MyAccountConstants.SHIPPING_FORM);
        Assert.assertTrue(isVisible, "Shipping Address form is not visible.");
    }

    public static void clickButtonForSection(String button, String section) {
        String lowerButton = button.toLowerCase();
        String lowerSection = section.toLowerCase();

        if (lowerButton.equals("edit")) {
            switch (lowerSection) {
                case "shipping address":
                    WebActionManager.click(MyAccountConstants.EDIT_SHIPPING_ADDRESS_BUTTON);
                    break;
                case "billing address":
                    WebActionManager.click(MyAccountConstants.EDIT_BILLING_ADDRESS_BUTTON);
                    break;
                default:
                    throw new IllegalArgumentException("Sección no reconocida para botón Edit: " + section);
            }
        } else {
            throw new IllegalArgumentException("Botón no soportado aún: " + button);
        }
    }

}
