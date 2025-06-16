package lippia.web.constants;

public class LoginConstants {

        public static final String USERNAME_EMAIL_FIELD = "xpath://input[@id='username']";

        public static final String LOGIN_PASSWORD_FIELD = "xpath://input[@id='password']";

        public static final String LOGIN_BUTTON = "xpath://*[@id='customer_login']//form//input[@type='submit' and @value='Login']";


        public static final String ERROR_MESSAGE = "xpath://ul[contains(@class,'woocommerce-error')]/li";

        public static final String SUCCESS_CONTENT = "xpath://div[contains(@class,'woocommerce-MyAccount-content')]";

        public static final String LOGOUT_BUTTON = "xpath://a[text()='Logout']";

        public static final String MY_ACCOUNT_MENU = "xpath://nav//a[contains(text(),'My Account')]";
}
