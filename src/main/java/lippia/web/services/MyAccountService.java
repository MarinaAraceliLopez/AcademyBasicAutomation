package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.MyAccountConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class MyAccountService {

    private static By byFromString(String locator) {
        return LocatorService.getBy(locator);
    }
    private static boolean isElementPresent(By by) {
        WebDriver driver = DriverManager.getDriverInstance();

        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    private static boolean isElementVisible(String locator) {
        try {
            return DriverManager.getDriverInstance()
                    .findElement(LocatorService.getBy(locator))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public static void clickMyAccountOption(String button) {
        String buttonLocator = String.format(MyAccountConstants.ACTION_BUTTON, button);
        WebActionManager.click(buttonLocator);
    }
    public static boolean isSectionVisible(String action) {
        if (action.equalsIgnoreCase("Account Details")) {
            return isElementVisible(MyAccountConstants.ACCOUNT_DETAILS_SECTION);
        }
        if (action.equalsIgnoreCase("Addresses")) {
            return isElementVisible(MyAccountConstants.ADDRESSES_SECTION);
        }
        if (action.equalsIgnoreCase("Orders")) {
            // Primero vemos si tiene la tabla de orders
            if (isElementVisible(MyAccountConstants.ORDERS_SECTION)) {
                return true;
            }
            // o, en ausencia de la tabla, que aparezca el mensaje de "sin pedidos"
            return isElementVisible(MyAccountConstants.NO_ORDERS_MESSAGE);
        }
        return false;
    }

    public static void clickEditShippingAddress() {
        WebActionManager.click(MyAccountConstants.SHIPPING_ADDRESS_EDIT);
    }
    public static boolean isShippingFormVisible(){
        return isElementVisible(MyAccountConstants.SHIPPING_ADDRESS_FORM);
    }

    public static void viewOrderDetails(){
        WebActionManager.click(MyAccountConstants.ORDER_VIEW_BUTTON);
    }

    public static boolean isOrderDetailsVisible(){
        return isElementVisible(MyAccountConstants.ORDER_DETAIL);
    }
    public static void performLogout(){
        WebActionManager.click(MyAccountConstants.LOGOUT_BUTTON);
    }
    public static boolean isLoggedOut(){
        return isElementPresent(byFromString("xpath://input[@id='username']"));
    }
//    public static boolean hasAnyOrder(){
//        return isElementPresent(byFromString(MyAccountConstants.FIRST_ORDER_IN_TABLE));
//    }
    public static boolean isWordPresentOnPage(String text) {
        return !DriverManager.getDriverInstance()
                .findElements(By.xpath("//*[contains(text(),'" + text + "')]")).isEmpty();
    }
    public static boolean hasAnyProductInCart(){
        return isElementPresent(byFromString(MyAccountConstants.CART_ITEMS));
    }

    public static boolean hasProductInCart(){
        return isElementPresent(byFromString(MyAccountConstants.CART_ITEM)); // true if at least 1 item
    }

    public static void changePassword(String currentPass, String newPass) {
        WebDriver driver = DriverManager.getDriverInstance();

        By currentPassField = byFromString(MyAccountConstants.CURRENT_PASS_FIELD);
        By newPassField = byFromString(MyAccountConstants.NEW_PASS_FIELD);
        By confirmPassField = byFromString(MyAccountConstants.CONFIRM_PASS_FIELD);
        By saveBtn = byFromString(MyAccountConstants.SAVE_CHANGES_BUTTON);

        driver.findElement(currentPassField).clear();
        driver.findElement(currentPassField).sendKeys(currentPass);
        driver.findElement(newPassField).clear();
        driver.findElement(newPassField).sendKeys(newPass);
        driver.findElement(confirmPassField).clear();
        driver.findElement(confirmPassField).sendKeys(newPass);
        driver.findElement(saveBtn).click();
    }

    public static boolean isChangeSuccessMessageVisible(){
        return isWordPresentOnPage("Account details changed successfully.");
    }
    public static void fillAccountDetails(String firstName, String lastName, String email) {
        WebDriver driver = DriverManager.getDriverInstance();

        driver.findElement(byFromString("xpath://input[@id='account_first_name']")).clear();
        driver.findElement(byFromString("xpath://input[@id='account_first_name']")).sendKeys(firstName);
        driver.findElement(byFromString("xpath://input[@id='account_last_name']")).clear();
        driver.findElement(byFromString("xpath://input[@id='account_last_name']")).sendKeys(lastName);
        driver.findElement(byFromString("xpath://input[@id='account_email']")).clear();
        driver.findElement(byFromString("xpath://input[@id='account_email']")).sendKeys(email);
    }

    public static void fillPasswords(String currentPass, String newPass) {
        WebDriver driver = DriverManager.getDriverInstance();

        driver.findElement(byFromString("xpath://input[@id='account_current_pass']")).clear();
        driver.findElement(byFromString("xpath://input[@id='account_current_pass']")).sendKeys(currentPass);
        driver.findElement(byFromString("xpath://input[@id='account_new_pass']")).clear();
        driver.findElement(byFromString("xpath://input[@id='account_new_pass']")).sendKeys(newPass);
        driver.findElement(byFromString("xpath://input[@id='account_confirm_pass']")).clear();
        driver.findElement(byFromString("xpath://input[@id='account_confirm_pass']")).sendKeys(newPass);
    }

    public static void saveAccountDetails(){
        WebDriver driver = DriverManager.getDriverInstance();
        driver.findElement(byFromString("xpath://button[@name='save_account_details']")).click();
    }
    public static boolean hasAnyOrder(){
        // Primero ir a Orders
        clickMyAccountOption("Orders");

        // Después verificar que aparece la primera orden en la tabla
        return isElementPresent(byFromString(MyAccountConstants.FIRST_ORDER_IN_TABLE));
    }

}
