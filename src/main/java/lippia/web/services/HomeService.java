package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.HomeConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static lippia.web.constants.HomeConstants.ADD_TO_BASKET_BUTTON;

public class HomeService {

    public static void openUrl(String url) {
        WebActionManager.navigateTo(url);
    }
    public static void clickShopMenu() {
        WebActionManager.click(HomeConstants.SHOP_MENU);
    }

    public static void clickHomeMenu() {
        WebActionManager.click(HomeConstants.HOME_MENU_BUTTON);
    }

    public static int countArrivals() {
        return WebActionManager.getElements(HomeConstants.ARRIVALS_SECTION).size();
    }

    public static void clickArrivalImage(int index) {
        WebActionManager.click("(" + HomeConstants.ARRIVAL_IMAGES + ")[" + index + "]");
    }

    public static boolean isAddToBasketButtonVisible() {
        return WebActionManager.isVisible(ADD_TO_BASKET_BUTTON);
    }

    public static void clickAddToBasket() {
        WebActionManager.click(ADD_TO_BASKET_BUTTON);
    }

    public static void clickProduct(int number) {
        String product = null;
        if (number == 1) product = HomeConstants.PRODUCT_1;
        else if (number == 2) product = HomeConstants.PRODUCT_2;
        else if (number == 3) product = HomeConstants.PRODUCT_3;

        if (product != null) {
            WebActionManager.click(product);
        }
    }



    public static boolean isBookInBasketWithPrice() {
        return WebActionManager.isVisible(HomeConstants.BASKET_MENU_ITEM) &&
                WebActionManager.isVisible(HomeConstants.BASKET_PRICE);
    }

    public static void clickBasketItemLink() {
        WebActionManager.click(HomeConstants.BASKET_ITEM_LINK);
    }

    public static double getTotalValue() {
        String totalText = WebActionManager.getText(HomeConstants.TOTAL_VALUE);
        return parsePrice(totalText);
    }

    public static double getSubtotalValue() {
        String subtotalText = WebActionManager.getText(HomeConstants.SUBTOTAL_VALUE);
        return parsePrice(subtotalText);
    }

    private static double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
    }

    public static void clickProceedToCheckout() {
        WebActionManager.click(HomeConstants.PROCEED_TO_CHECKOUT_BUTTON);
    }

    public static boolean isPaymentPageVisible() {
        return WebActionManager.isVisible(HomeConstants.BILLING_DETAILS_FORM);
    }

    public static void fillBillingDetails(String name, String email, String phone, String address) {
        WebActionManager.setInput("id=billing_first_name", name);
        WebActionManager.setInput("id=billing_email", email);
        WebActionManager.setInput("id=billing_phone", phone);
        WebActionManager.setInput("id=billing_address_1", address);
    }

    public static void selectPaymentMethod(String paymentMethod) {
        String locator = "";
        switch (paymentMethod.toLowerCase()) {
            case "bank transfer":
                locator = HomeConstants.PAYMENT_METHOD_BANK_TRANSFER;
                break;
            case "cheque":
                locator = HomeConstants.PAYMENT_METHOD_CHEQUE;
                break;
            case "cash":
                locator = HomeConstants.PAYMENT_METHOD_CASH;
                break;
            case "paypal":
                locator = HomeConstants.PAYMENT_METHOD_PAYPAL;
                break;
        }
        WebActionManager.click(locator);
    }

    public static void applyCoupon(String couponCode) {
        WebActionManager.setInput(HomeConstants.COUPON_FIELD, couponCode);
        WebActionManager.click(HomeConstants.APPLY_COUPON_BUTTON);
    }

    public static void clickPlaceOrder() {
        WebActionManager.click(HomeConstants.PLACE_ORDER_BUTTON);
    }

    public static boolean isOrderConfirmationPageVisible() {
        return WebActionManager.isVisible(HomeConstants.ORDER_CONFIRMATION_DETAILS);
    }

    public static boolean isBillingDetailsVisible() {
        return WebActionManager.isVisible(HomeConstants.BILLING_DETAILS_FORM);
    }

    public static boolean isOrderDetailsVisible() {
        return WebActionManager.isVisible(HomeConstants.ORDER_DETAILS);
    }

    public static boolean isAdditionalDetailsVisible() {
        return WebActionManager.isVisible(HomeConstants.ADDITIONAL_DETAILS);
    }

    public static boolean isPaymentGatewayVisible() {
        return WebActionManager.isVisible(HomeConstants.PAYMENT_GATEWAY);
    }

    public static boolean isOrderDetailsVisibleOnConfirmation() {
        return WebActionManager.isVisible(HomeConstants.CONFIRMATION_ORDER);
    }

    public static boolean isBankDetailsVisible() {
        return WebActionManager.isVisible(HomeConstants.CONFIRMATION_BANK);
    }

    public static boolean isCustomerDetailsVisible() {
        return WebActionManager.isVisible(HomeConstants.CONFIRMATION_CUSTOMER);
    }

    public static boolean isBillingDetailsVisibleConfirmation() {
        return WebActionManager.isVisible(HomeConstants.CONFIRMATION_BILLING);
    }

    private static WebDriver driver = DriverManager.getDriverInstance();

    // Localizador de Arrivals en Home page
    private static final By ARRIVALS = By.cssSelector(".products .product");

    // Localizador de Sliders en Home page
    private static final By SLIDERS = By.cssSelector("#nivoslider .nivo-slide");

    public static int getArrivalCount() {
        List<WebElement> arrivals = driver.findElements(ARRIVALS);
        return arrivals.size();
    }

    public static int getSlidersCount() {
        List<WebElement> sliders = driver.findElements(SLIDERS);
        return sliders.size();
    }

//    public static boolean isAddToBasketVisible(WebDriver driver) {
//        By ADD_TO_BASKET_BUTTON = By.cssSelector("button.single_add_to_cart_button");
//
//        try {
//            return driver.findElement(ADD_TO_BASKET_BUTTON).isDisplayed();
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }





}


