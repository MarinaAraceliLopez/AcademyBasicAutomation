package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.MyAccountConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static lippia.web.constants.MyAccountConstants.*;


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

            if (isElementVisible(MyAccountConstants.ORDERS_SECTION)) {
                return true;
            }
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
        return isElementPresent(byFromString(MyAccountConstants.USERNAME_INPUT));
    }


    public static boolean isWordPresentOnPage(String expectedText) {
        WebDriver driver = DriverManager.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String formattedXpath = String.format(
                MyAccountConstants.TEXT_CONTAINS_IGNORE_CASE,
                expectedText.toLowerCase()
        );

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(formattedXpath.replace("xpath:", ""))));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public static boolean hasAnyProductInCart(){
        return isElementPresent(byFromString(MyAccountConstants.CART_ITEMS));
    }

    public static boolean hasProductInCart(){
        return isElementPresent(byFromString(MyAccountConstants.CART_ITEM));
    }

    public static void changePassword(String currentPass, String newPass) {
        WebDriver driver = DriverManager.getDriverInstance();

        By currentPassField = byFromString(MyAccountConstants.CURRENT_PASS_FIELD);
        By newPassField = byFromString(NEW_PASS_FIELD);
        By confirmPassField = byFromString(CONFIRM_PASS_FIELD);
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

        driver.findElement(byFromString(ACCOUNT_FIRST_NAME_INPUT)).clear();
        driver.findElement(byFromString(ACCOUNT_FIRST_NAME_INPUT)).sendKeys(firstName);

        driver.findElement(byFromString(ACCOUNT_LAST_NAME_INPUT)).clear();
        driver.findElement(byFromString(ACCOUNT_LAST_NAME_INPUT)).sendKeys(lastName);

        driver.findElement(byFromString(ACCOUNT_EMAIL_INPUT)).clear();
        driver.findElement(byFromString(ACCOUNT_EMAIL_INPUT)).sendKeys(email);
    }

    public static void fillPasswords(String currentPass, String newPass) {
        WebDriver driver = DriverManager.getDriverInstance();

        driver.findElement(byFromString(CURRENT_PASS_FIELD)).clear();
        driver.findElement(byFromString(CURRENT_PASS_FIELD)).sendKeys(currentPass);

        driver.findElement(byFromString(NEW_PASS_FIELD)).clear();
        driver.findElement(byFromString(NEW_PASS_FIELD)).sendKeys(newPass);

        driver.findElement(byFromString(CONFIRM_PASS_FIELD)).clear();
        driver.findElement(byFromString(CONFIRM_PASS_FIELD)).sendKeys(newPass);
    }

    public static void saveAccountDetails(){
        WebDriver driver = DriverManager.getDriverInstance();
        driver.findElement(byFromString("xpath://button[@name='save_account_details']")).click();
    }

    public static boolean hasAnyOrder(){
        clickMyAccountOption("Orders");
        return isElementPresent(byFromString(MyAccountConstants.FIRST_ORDER_IN_TABLE));
    }
    public static void waitForPageLoad() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public static void validateAccountGreeting() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, 15);

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'From your account dashboard')]")));

            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.contains("Hello"), "'Hello' no encontrado en el DOM.");
            Assert.assertTrue(pageSource.contains("From your account dashboard"),
                    "'From your account dashboard' no visible en el DOM.");

            System.out.println("Saludo y mensaje de dashboard visibles.");
        } catch (TimeoutException e) {
            Assert.fail("El saludo o el texto del dashboard no se visualizaron a tiempo.");
        }
    }

    public static void handleAccountButtonClick(String button, String context) {
        if (context.equalsIgnoreCase("Shipping Address") && button.equalsIgnoreCase("Edit")) {
            clickEditShippingAddress();
        } else {
            Assert.fail("Unknown button \"" + button + "\" for context \"" + context + "\"");
        }
    }
}
