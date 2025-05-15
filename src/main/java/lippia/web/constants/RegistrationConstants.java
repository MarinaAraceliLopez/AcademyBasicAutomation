package lippia.web.constants;

public class RegistrationConstants {
   
    public static final String MENU_MY_ACCOUNT_LOGIN = "xpath://nav//ul//li//a[contains(text(),'My Account')]"
            ;


    // Selectores para los campos de login
    public static final String REGISTRATION_EMAIL = "xpath://*[@id=\"reg_email\"]";
    public static final String REGISTRATION_PASSWORD = "xpath://*[@id=\"reg_password\"]";
    public static final String REGISTER_BUTTON = "xpath://*[@id=\"customer_login\"]/div[2]/form/p[3]/input[3]";


    public static final String ERROR_MESSAGE = "xpath://*[@id=\"page-36\"]/div/div[1]/ul/li";
    public static final String SUCCESS_CONTENT = "xpath://*[@id=\"page-36\"]/div/div[1]";
    public static final String REGISTRATION_FORM = "xpath://*[@id=\"customer_login\"]/div[2]/form";

}
