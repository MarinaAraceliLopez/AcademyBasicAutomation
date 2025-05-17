package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.MyAccountConstants;
import org.testng.Assert;

public class MyAccountService {

    public static void verifyWelcomeMessage(String expectedMessage) {
        String actualMessage = WebActionManager.getText(MyAccountConstants.WELCOME_MESSAGE);
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected: '" + expectedMessage + "' but found: '" + actualMessage + "'");
    }

    public static void clickOnAccountButton(String button) {
        String xpath;
        switch (button.toLowerCase()) {
            case "logout":
                xpath = MyAccountConstants.LOGOUT_BUTTON;
                break;
            case "account details":
                xpath = MyAccountConstants.ACCOUNT_DETAILS_BUTTON;
                break;
            case "addresses":
                xpath = MyAccountConstants.ADDRESS_BUTTON;
                break;
            case "orders":
                xpath = MyAccountConstants.ORDERS_BUTTON;
                break;
            case "edit shipping":
                xpath = MyAccountConstants.EDIT_SHIPPING_ADDRESS_BUTTON;
                break;
            default:
                throw new IllegalArgumentException("El botón '" + button + "' no está definido.");
        }
        WebActionManager.click(xpath);
    }



   // public static void enterValueInField(String value, String fieldName) {
    //    String xpath;
//
   //     switch (fieldName.toLowerCase()) {
    //       case "login email":
    //             xpath = MyAccountConstants.LOGIN_EMAIL_FIELD;
    //            break;
    //           case "login password":
    //             xpath = MyAccountConstants.LOGIN_PASSWORD_FIELD;
    //              break;
    //          default:
    //              throw new IllegalArgumentException("El campo '" + fieldName + "' no está definido.");
    //     }
//
    //   WebActionManager.setInput(xpath, value);  // <--- Sin By.xpath
    //}


    public static void validateTextIsPresent(String expectedText) {
        String xpath = "//*[contains(text(),'" + expectedText + "')]";
        Assert.assertTrue(WebActionManager.isVisible(xpath),
                "El texto esperado '" + expectedText + "' no está visible en la página.");
    }


    //   public static void validateMessage(String expectedText) {
      //   String xpath = MyAccountConstants.RESULT_MESSAGE;
    //     String actualText = WebActionManager.getText(xpath);
    //   Assert.assertTrue(actualText.contains(expectedText),
   //             "Mensaje esperado: '" + expectedText + "', pero se encontró: '" + actualText + "'");
  // }

    public static void validateShippingFormIsVisible() {
        Assert.assertTrue(WebActionManager.isVisible(MyAccountConstants.SHIPPING_FORM),
                "El formulario de dirección de envío no es visible.");
    }
    public static void validateOrderHistoryIsPresent() {
        Assert.assertTrue(WebActionManager.isVisible(MyAccountConstants.ORDER_HISTORY_SECTION),
                "No se encontró ninguna orden en la cuenta del usuario.");
    }
    public static void validateOrderDetailIsVisible() {
        Assert.assertTrue(WebActionManager.isVisible(MyAccountConstants.ORDER_DETAIL_SECTION),
                "Los detalles del pedido no están visibles.");
    }
    public static void clickViewOrderButton() {
        WebActionManager.click(MyAccountConstants.VIEW_ORDER_BUTTON);
    }


    public static void validateSectionIsVisible(String action) {
        String xpath;
        switch (action.toLowerCase()) {
            case "account details":
                xpath = "xpath://h2[contains(text(),'Account Details')]";
                break;
            case "addresses":
                xpath = "xpath://h3[contains(text(),'Billing Address') or contains(text(),'Shipping Address')]";
                break;
            case "orders":
                xpath = "xpath://*[@id='page-36']//h2[contains(text(),'Orders')] | //*[@id='page-36']//*[contains(text(),'No order has been made yet')]";
                break;
            default:
                throw new IllegalArgumentException("La acción '" + action + "' no está definida para validar.");
        }

        Assert.assertTrue(WebActionManager.isVisible(xpath),
                "No se encontró el contenido esperado para la acción '" + action + "'");
    }

    public static void verifyLogout() {
        Assert.assertTrue(WebActionManager.isVisible(MyAccountConstants.LOGIN_FORM),
                "El formulario de login no es visible, posible error al cerrar sesión.");
    }




}
