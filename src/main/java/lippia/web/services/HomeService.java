package lippia.web.services;
import com.crowdar.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static lippia.web.services.ShopService.performAction;


public class HomeService {
    private static final By ADD_TO_BASKET_HOME = By.xpath("//*[@id=\"product-160\"]/div[2]/form/button");
    private static final By VIEW_BASKET_HOME = By.xpath("//a[contains(@class,'added_to_cart') or contains(text(),'View Basket')]");
    private static final By CHECK_BASKET_HOME = By.xpath("//*[@id=\"page-34\"]/div/div[1]/div/div/div/a");
    private static final By ARRIVALS = By.cssSelector(".products .product");
    private static final By SLIDERS = By.cssSelector("#n2-ss-6 .n2-ss-slide-background");

    // 1,2 Scenario: Home page shows 3 Arrivals

    public static void validateArrivalsCountIsThree() {
        WebDriver driver = DriverManager.getDriverInstance();
        List<WebElement> arrivals = driver.findElements(ARRIVALS);
        if (arrivals.size() != 3) {
            throw new AssertionError("❌ Se esperaban 3 'Arrivals' pero se encontraron: " + arrivals.size());
        }
        System.out.println("✅ Hay 3 arrivals visibles en la Home.");
    }

    public static void validateSlidersCountIsThree() {
        WebDriver driver = DriverManager.getDriverInstance();
        List<WebElement> sliders = driver.findElements(SLIDERS);
        if (sliders.size() != 3) {
            throw new AssertionError("❌ Se esperaban 3 sliders pero se encontraron: " + sliders.size());
        }
        System.out.println("✅ Hay 3 sliders en la Home.");
    }

// 4,5 Scenario: Complete the order process and verify order confirmation page

    public static void addProductFromHome() {
        performAction(ADD_TO_BASKET_HOME);
    }

    public static void viewBasketFromHome() {
        performAction(VIEW_BASKET_HOME);
    }

    public static void checkBasketHome() {
        performAction(CHECK_BASKET_HOME);
    }


    private static final By ARRIVAL_IMAGE = By.xpath("//img[contains(@class,'attachment-shop_catalog')]");
    private static final By ADD_TO_BASKET_BTN = By.cssSelector(".single_add_to_cart_button");

    public static void clickAnyArrivalImage() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, 15);

        WebElement arrival = wait.until(ExpectedConditions.elementToBeClickable(ARRIVAL_IMAGE));
        arrival.click();
        System.out.println("🖼️ Imagen Arrival clickeada.");
    }

    public static void validateBookDetailPageHasAddToBasket() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_BASKET_BTN));
        if (!button.isDisplayed()) {
            throw new AssertionError("❌ Botón 'Add to Basket' no visible.");
        }

        System.out.println("📘 Página del libro tiene botón 'Add to Basket'.");
    }
}






