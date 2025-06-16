package lippia.web.constants;

public class MyAccountConstants {
        public static final String WELCOME_MESSAGE = "xpath://div[contains(@class,'woocommerce-MyAccount-content')]//p[contains(text(),'Hello')]";
        public static final String LOGOUT_BUTTON = "xpath://a[text()='Logout']";
        public static final String ACCOUNT_DETAILS_BUTTON = "xpath://a[text()='Account Details']";
        public static final String ADDRESS_BUTTON = "xpath://a[text()='Addresses']";
        public static final String ORDERS_BUTTON = "xpath://a[text()='Orders']";
        public static final String EDIT_SHIPPING_ADDRESS_BUTTON = "xpath://h3[text()='Shipping Address']/following-sibling::a[text()='Edit']";
        public static final String SHIPPING_FORM = "xpath://form[@action='https://practice.automationtesting.in/my-account/edit-address/shipping/']";
        public static final String RESULT_MESSAGE = "xpath://div[@class='woocommerce-message']";
        public static final String ORDER_HISTORY_SECTION = "xpath://section[contains(@class,'woocommerce-orders')]";
        public static final String LOGIN_FORM = "xpath://form[@class='login']";
        public static final String LOGIN_EMAIL_FIELD = "xpath://input[@id='username']";
        public static final String LOGIN_PASSWORD_FIELD = "xpath://input[@id='password']";
        public static final String ORDER_DETAIL_SECTION = "xpath://section[contains(@class,'woocommerce-order')]";
        public static final String VIEW_ORDER_BUTTON = "xpath://a[contains(@class, 'view') and text()='View']";
        public static final String CURRENT_PASSWORD_FIELD = "xpath://input[@id='password_current']";
        public static final String NEW_PASSWORD_FIELD = "xpath://input[@id='password_1']";
        public static final String CONFIRM_PASSWORD_FIELD = "xpath://input[@id='password_2']";
        public static final String SAVE_CHANGES_BUTTON = "xpath://input[@name='save_account_details']";
        public static final String MY_ACCOUNT_LINK = "xpath://a[contains(text(),'My Account')]";
        public static final String FIRST_NAME_FIELD = "xpath://input[@id='account_first_name']";
        public static final String LAST_NAME_FIELD = "xpath://input[@id='account_last_name']";
        public static final String CART_PRODUCT_PRICE = "css:.cart-price";
}
