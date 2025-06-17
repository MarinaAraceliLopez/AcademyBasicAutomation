package lippia.web.constants;

public class HomeConstants {

    public static final String SHOP_MENU = "//a[text()='Shop']";
    public static final String HOME_MENU = "//a[text()='Home']";
    public static final String ARRIVALS_SECTION = "//section[contains(@class,'arrivals')]//li";
    public static final String ARRIVAL_IMAGES = "//section[contains(@class,'arrivals')]//li//img";
    public static final String ADD_TO_BASKET_BUTTON = "//button[contains(text(),'Add to basket')]";
    public static final String BASKET_MENU_ITEM = "//a[contains(@class,'cartcontents')]";
    public static final String BASKET_PRICE = "//span[contains(@class,'amount')]";
    public static final String BASKET_ITEM_LINK = "//a[contains(@class,'wpmenucart-contents')]";
    public static final String TOTAL_VALUE = "//td[contains(text(),'Total')]/following-sibling::td";
    public static final String SUBTOTAL_VALUE = "//td[contains(text(),'Subtotal')]/following-sibling::td";
    public static final String PROCEED_TO_CHECKOUT_BUTTON = "//a[contains(@class,'checkout-button')]";
    public static final String BILLING_DETAILS_FORM = "//*[@id='billing_form']";
    public static final String PLACE_ORDER_BUTTON = "//button[@id='place_order']";


    // Payment methods
    public static final String PAYMENT_METHOD_BANK_TRANSFER = "//input[@id='payment_method_bacs']";
    public static final String PAYMENT_METHOD_CHEQUE = "//input[@id='payment_method_cheque']";
    public static final String PAYMENT_METHOD_CASH = "//input[@id='payment_method_cod']";
    public static final String PAYMENT_METHOD_PAYPAL = "//input[@id='payment_method_ppec_paypal']";

    public static final String COUPON_FIELD = "//input[@name='coupon_code']";
    public static final String APPLY_COUPON_BUTTON = "//button[@name='apply_coupon']";

    public static final String ORDER_CONFIRMATION_DETAILS = "//*[@id='order_received']";
}

