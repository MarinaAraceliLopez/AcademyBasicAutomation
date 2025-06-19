package lippia.web.constants;

public class MyAccountConstants {

    public static final String ACTION_BUTTON = "xpath://a[text()='%s']";
    public static final String ADDRESSES_SECTION = "xpath://*[@id='page-36']/div/div[1]/div";
    public static final String ACCOUNT_DETAILS_SECTION = "xpath://*[@id='page-36']/div/div[1]/div/form/fieldset";
    public static final String ORDERS_SECTION = "xpath://*[@id='page-36']//table";
    public static final String NO_ORDERS_MESSAGE = "xpath://*[@id='page-36']//p[contains(text(), 'No order has been made yet')]";
    public static final String SHIPPING_ADDRESS_EDIT = "xpath://*[@id='page-36']/div/div[1]/div/div/div[2]/header/a";
    public static final String SHIPPING_ADDRESS_FORM = "xpath://*[@id='page-36']/div/div[1]/div/form/h3";

    public static final String ORDER_VIEW_BUTTON = "xpath://*[@id='page-36']//table//tr[1]//a[contains(text(), 'View')]";
    public static final String ORDER_DETAIL = "xpath://*[@id='page-36']/div/div[1]/div/h2";
    public static final String LOGOUT_BUTTON = "xpath://a[text()='Logout']";
    public static final String FIRST_ORDER_IN_TABLE = "xpath://table[contains(@class,'orders')]//tr[1]";
    public static final String CART_ITEMS = "xpath://div[contains(@class,'cart-item')]";
    public static final String CART_ITEM = "xpath://table[@class='shop_table']//tr[1]";

    public static final String CURRENT_PASS_FIELD = "xpath://input[@id='account_current_pass']";
    public static final String NEW_PASS_FIELD = "xpath://input[@id='account_new_pass']";
    public static final String CONFIRM_PASS_FIELD = "xpath://input[@id='account_confirm_pass']";
    public static final String SAVE_CHANGES_BUTTON = "xpath://button[@name='save_account_details']";


    public static final String ACCOUNT_FIRST_NAME_INPUT = "xpath://input[@id='account_first_name']";
    public static final String ACCOUNT_LAST_NAME_INPUT = "xpath://input[@id='account_last_name']";
    public static final String ACCOUNT_EMAIL_INPUT = "xpath://input[@id='account_email']";
    public static final String USERNAME_INPUT = "xpath://input[@id='username']";
    public static final String TEXT_CONTAINS_IGNORE_CASE = "xpath://*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '%s')]";



}
