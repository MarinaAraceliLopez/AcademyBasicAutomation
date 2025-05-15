package lippia.web.constants;

public class ShopConstants {


    public static final String FILTER_BUTTON = "xpath://*[@id=\"woocommerce_price_filter-2\"]/form/div/div[2]/button";

    public static final String PRODUCT_PRICES = "xpath://*[@id=\"content\"]/ul/li[3]/a[1]/span";

    public static final String SORT_DROPDOWN = "xpath://*[@id=\"content\"]/form/select";

    public static final String PRODUCTS_LIST = "xpath://*[@id=\"content\"]/ul";


    public static final String SALE_PRODUCT_FIRST = "xpath:(//span[contains(text(),'Sale!')])[1]";
    public static final String SALE_PRODUCT_SECOND = "xpath:(//span[contains(text(),'Sale!')])[2]";

    public static final String OLD_PRICE = "xpath://div[contains(@id, 'product-')]/div[2]/div[1]/p/del/span";
    public static final String NEW_PRICE = "xpath://div[contains(@id, 'product-')]/div[2]/div[1]/p/ins";

}
