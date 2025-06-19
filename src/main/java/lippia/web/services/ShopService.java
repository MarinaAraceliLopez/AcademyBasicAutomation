package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import junit.framework.Assert;
import lippia.web.constants.ShopConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;




import java.util.List;


public class ShopService {

    public static void clickShopMenu() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement shopMenu = driver.findElement(By.cssSelector("a[href*='/shop/']"));
        shopMenu.click();
    }

    public static void setPriceRangeByValues(int targetMinPrice, int targetMaxPrice, int priceMin, int priceMax) {
        WebDriver driver = DriverManager.getDriverInstance();

        WebElement slider = driver.findElement(By.cssSelector(".ui-slider"));
        int sliderWidthPx = slider.getSize().getWidth();

        int leftHandleMovementPx = (targetMinPrice - priceMin) * sliderWidthPx / (priceMax - priceMin);
        int rightHandleMovementPx = - (priceMax - targetMaxPrice) * sliderWidthPx / (priceMax - priceMin);

        List<WebElement> handles = driver.findElements(By.cssSelector(".ui-slider-handle"));
        WebElement leftHandle = handles.get(0);
        WebElement rightHandle = handles.get(1);

        Actions actions = new Actions(driver);
        actions.dragAndDropBy(leftHandle, leftHandleMovementPx, 0).perform();
        actions.dragAndDropBy(rightHandle, rightHandleMovementPx, 0).perform();
    }

    public static void clickFilterButton() {
        WebDriver driver = DriverManager.getDriverInstance();
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public static void verifyPricesInRange(int min, int max) {
        List<WebElement> priceContainers = DriverManager.getDriverInstance()
                .findElements(By.cssSelector(".products li .price"));

        for (WebElement priceContainer : priceContainers) {
            String priceText;
            if (priceContainer.findElements(By.tagName("ins")).size() > 0) {
                priceText = priceContainer.findElement(By.tagName("ins")).getText();
            } else {
                priceText = priceContainer.getText();
            }

            priceText = priceText.replaceAll("[^\\d.]", "");

            double price = Double.parseDouble(priceText);
            if (price < min || price > max) {
                throw new AssertionError("El precio " + price + " está fuera del rango (" + min + " - " + max + ")");
            }
        }
    }

    public static void selectSortingOption(String optionVisibleText) {
        WebDriver driver = DriverManager.getDriverInstance();
        Select dropdown = new Select(driver.findElement(By.name("orderby")));
        dropdown.selectByVisibleText(optionVisibleText);
    }

    public static void verifyProductsVisible() {
        WebDriver driver = DriverManager.getDriverInstance();
        List<WebElement> products = driver.findElements(By.cssSelector(".products li"));
        if (products.isEmpty()) {
            throw new AssertionError("No se encontraron productos.");
        }
    }

    public static void clickSaleProduct(String position) {
        WebDriver driver = DriverManager.getDriverInstance();
        List<WebElement> saleProducts = driver.findElements(By.cssSelector(".products li .onsale"));

        int index = position.equalsIgnoreCase("first") ? 0 : saleProducts.size() - 1;
        saleProducts.get(index).click();
    }

    public static void verifyPriceWithStrikeThrough() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement oldPrice = driver.findElement(By.cssSelector(".price del"));
        WebElement newPrice = driver.findElement(By.cssSelector(".price ins"));
        if (!oldPrice.isDisplayed() || !newPrice.isDisplayed()) {
            throw new AssertionError("No se muestra el precio antiguo o el nuevo correctamente.");
        }
    }

    //5 y 6 Scenario: The user clicks on the first sale product on the home page

        private static final By ADD_TO_BASKET_BTN = By.xpath("//*[@id='content']/ul/li[1]/a[2]");
        private static final By VIEW_BASKET_BTN = By.xpath("//*[@id='wpmenucartli']/a");
        private static final By PROCEED_TO_CHECKOUT_BTN = By.xpath("//*[@id='page-34']/div/div[1]/div/div/div/a");
        private static final By PLACE_ORDER_BTN = By.id("place_order");

        private static final By SUBTOTAL_LOCATOR = By.xpath("//*[@id='order_review']/table/tfoot/tr[1]/td");
        private static final By TAX_LOCATOR = By.xpath("//*[@id='order_review']/table/tfoot/tr[2]/td");
        private static final By TOTAL_LOCATOR = By.xpath("//*[@id='order_review']/table/tfoot/tr[3]/td");

    public static void performAction(By locator) {
        WebDriver driver = DriverManager.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            Thread.sleep(500);
        } catch (StaleElementReferenceException staleEx) {
            // Retry una vez si el elemento quedó obsoleto
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError("❌ Interrupción inesperada.", e);
        } catch (Exception e) {
            throw new AssertionError("❌ No se pudo interactuar con el elemento: " + locator, e);
        }
    }



    public static void addBookToBasket() {
            performAction(ADD_TO_BASKET_BTN);
        }

        public static void viewBasket() {
            performAction(VIEW_BASKET_BTN);
        }

        public static void proceedToCheckout() {
            performAction(PROCEED_TO_CHECKOUT_BTN);
        }

        public static void clickPlaceOrder() {
            performAction(PLACE_ORDER_BTN);
        }

    public static void fillBillingDetailsForm(String country) {
        WebDriver driver = DriverManager.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_first_name"))).sendKeys("Juan");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_last_name"))).sendKeys("Pérez");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_email"))).sendKeys("juan.perez@example.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_phone"))).sendKeys("1234567890");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_address_1"))).sendKeys("Calle Falsa 123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_city"))).sendKeys("Ciudad");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_postcode"))).sendKeys("12345");

        WebElement countryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_country")));
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText(country);

        System.out.println("✅ País seleccionado: " + country);
    }

    public static void selectPaymentMethod(String method) {
            WebDriver driver = DriverManager.getDriverInstance();
            WebElement paymentOption;
            switch (method) {
                case "Direct bank transfer":
                    paymentOption = driver.findElement(By.xpath("//*[@id='payment']/ul/li[1]/label"));
                    break;
                case "Check Payments":
                    paymentOption = driver.findElement(By.xpath("//*[@id='payment']/ul/li[2]/label"));
                    break;
                case "Cash on Delivery":
                    paymentOption = driver.findElement(By.xpath("//*[@id='payment']/ul/li[3]/label"));
                    break;
                case "PayPal":
                    paymentOption = driver.findElement(By.xpath("//*[@id='payment']/ul/li[4]/label"));
                    break;
                default:
                    throw new IllegalArgumentException("Método de pago no soportado: " + method);
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentOption);
            System.out.println("💳 Método de pago seleccionado: " + method);
        }

    public static void validateSubtotalTotalAndTax() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebDriverWait wait = new WebDriverWait(driver, 15);

        double subtotal = parseAmount(wait.until(ExpectedConditions.visibilityOfElementLocated(SUBTOTAL_LOCATOR)).getText());
        double tax = parseAmount(wait.until(ExpectedConditions.visibilityOfElementLocated(TAX_LOCATOR)).getText());
        double total = parseAmount(wait.until(ExpectedConditions.visibilityOfElementLocated(TOTAL_LOCATOR)).getText());

        double expectedTotal = subtotal + tax;

        if (total < subtotal) {
            throw new AssertionError("❌ El total no puede ser menor al subtotal. Subtotal: " + subtotal + " Total: " + total);
        }

        if (Math.abs(total - expectedTotal) > 0.01) {
            throw new AssertionError("❌ Total no coincide con subtotal + impuesto. Esperado: " + expectedTotal + " pero fue: " + total);
        }


        System.out.println("✅ Total válido. Subtotal: " + subtotal + " | Impuesto: " + tax + " | Total: " + total);
    }


    private static double parseAmount(String text) {
        String clean = text.replaceAll("[^\\d.,]", "").replace(",", ".");
        try {
            return Double.parseDouble(clean);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("No se pudo parsear el número: " + text, e);
        }
    }
}