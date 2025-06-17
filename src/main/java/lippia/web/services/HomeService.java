package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.HomeConstants;


public class HomeService {

    public static void clickShopMenu() {
        WebActionManager.click(HomeConstants.SHOP_MENU);
    }

    public static void clickHomeMenu() {
        WebActionManager.click(HomeConstants.HOME_MENU);
    }

    public static int countArrivals() {
        return WebActionManager.getElements(HomeConstants.ARRIVALS_SECTION).size();
    }

    public static void clickArrivalImage(int index) {
        WebActionManager.click("(" + HomeConstants.ARRIVAL_IMAGES + ")[" + index + "]");
    }

    public static boolean isAddToBasketButtonVisible() {
        return WebActionManager.isVisible(HomeConstants.ADD_TO_BASKET_BUTTON);
    }

    public static void clickAddToBasket() {
        WebActionManager.click(HomeConstants.ADD_TO_BASKET_BUTTON);
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
}
