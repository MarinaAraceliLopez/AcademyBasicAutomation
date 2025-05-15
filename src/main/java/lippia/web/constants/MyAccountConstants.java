package lippia.web.constants;

public class MyAccountConstants {

    // MENSAJES
    public static final String WELCOME_MESSAGE = "xpath://div[@class='woocommerce-MyAccount-content']/p";
    public static final String RESULT_MESSAGE = "xpath://div[@class='woocommerce-MyAccount-content']//p";

    // BOTONES DE NAVEGACIÓN PRINCIPAL EN MY ACCOUNT
    public static final String LOGOUT_BUTTON = "xpath://a[contains(text(),'Logout')]";
    public static final String ACCOUNT_DETAILS_BUTTON = "xpath://a[contains(text(),'Account Details')]";
    public static final String ADDRESS_BUTTON = "xpath://a[contains(text(),'Addresses')]";
    public static final String ORDERS_BUTTON = "xpath://a[contains(text(),'Orders')]";

    // BOTONES DE EDICIÓN DE DIRECCIONES
    public static final String EDIT_SHIPPING_ADDRESS_BUTTON = "xpath://div[@class='u-column2 col-2 woocommerce-Address']//a[contains(text(),'Edit')]";
    public static final String EDIT_BILLING_ADDRESS_BUTTON = "xpath://div[@class='u-column1 col-1 woocommerce-Address']//a[contains(text(),'Edit')]";

    // FORMULARIOS
    public static final String SHIPPING_FORM = "xpath://form[@method='post']"; // Revisar si sirve para billing también o crear uno aparte si es necesario
}
