package lippia.web.constants;

import org.openqa.selenium.By;

public class ShopConstants {



    public static final By SORT_DROPDOWN = By.xpath("//*[@id='content']/form/select");

    public static final By PRODUCTS_LIST = By.cssSelector("#content ul.products > li");


    public static final String SALE_PRODUCT_FIRST = "xpath:(//span[contains(text(),'Sale!')])[1]";
    public static final String SALE_PRODUCT_SECOND = "xpath:(//span[contains(text(),'Sale!')])[2]";

    public static final By OLD_PRICE = By.xpath("//p/del/span");
    public static final By NEW_PRICE = By.xpath("//p/ins/span");
}
