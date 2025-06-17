package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import lippia.web.constants.ShopConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//    public static void clickAddToBasketButton(String buttonText) {
//        WebDriver driver = DriverManager.getDriverInstance();
//        WebElement button = driver.findElement(By.xpath("//a[contains(text(),'" + buttonText + "')]"));
//        button.click();
//    }

    public static void verifyBookInBasketWithPrice() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement basket = driver.findElement(By.cssSelector(".wpmenucart-contents"));
        if (!basket.isDisplayed()) {
            throw new AssertionError("El libro no está en el carrito.");
        }
    }

    public static void clickBasketMenuLink() {
        WebDriver driver = DriverManager.getDriverInstance();
        driver.findElement(By.cssSelector(".wpmenucart-contents")).click();
    }

    public static void verifyOnCheckoutPage() {
        WebDriver driver = DriverManager.getDriverInstance();
        if (!driver.getCurrentUrl().contains("checkout")) {
            throw new AssertionError("No estamos en la página de checkout.");
        }
    }

    public static void verifyTotalAndSubtotalVisible(String buttonText) {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement subtotal = driver.findElement(By.cssSelector(".cart-subtotal .amount"));
        WebElement total = driver.findElement(By.cssSelector(".order-total .amount"));
        if (!subtotal.isDisplayed() || !total.isDisplayed()) {
            throw new AssertionError("Subtotal o total no se muestra.");
        }
    }

    public static void verifyTotalLessThanSubtotal() {
        WebDriver driver = DriverManager.getDriverInstance();
        String subtotalText = driver.findElement(By.cssSelector(".cart-subtotal .amount")).getText().replaceAll("[^\\d.]", "");
        String totalText = driver.findElement(By.cssSelector(".order-total .amount")).getText().replaceAll("[^\\d.]", "");
        double subtotal = Double.parseDouble(subtotalText);
        double total = Double.parseDouble(totalText);
        if (total >= subtotal) {
            throw new AssertionError("El total no es menor que el subtotal.");
        }
    }

    public static void verifyOnPaymentGatewayPage() {
        WebDriver driver = DriverManager.getDriverInstance();
        if (!driver.getCurrentUrl().contains("order-received")) {
            throw new AssertionError("No estamos en la página de confirmación de pago.");
        }
    }

    public static void verifyDetailsSectionsVisible() {
        WebDriver driver = DriverManager.getDriverInstance();
        if (driver.findElements(By.xpath("//h3[text()='Billing Details']")).isEmpty()) {
            throw new AssertionError("Faltan los Billing Details.");
        }
    }

    public static void fillBillingDetailsForm() {
        WebDriver driver = DriverManager.getDriverInstance();

        driver.findElement(By.id("billing_first_name")).sendKeys("Juan");
        driver.findElement(By.id("billing_last_name")).sendKeys("Pérez");
        driver.findElement(By.id("billing_email")).sendKeys("juan.perez@example.com");
        driver.findElement(By.id("billing_phone")).sendKeys("1234567890");
        driver.findElement(By.id("billing_address_1")).sendKeys("Calle Falsa 123");
        driver.findElement(By.id("billing_city")).sendKeys("Ciudad");
        driver.findElement(By.id("billing_postcode")).sendKeys("12345");

        Select countrySelect = new Select(driver.findElement(By.id("billing_country")));
        countrySelect.selectByVisibleText("India");
    }
    public static void selectPaymentMethod(String method) {
        WebDriver driver = DriverManager.getDriverInstance();
        if (method.equalsIgnoreCase("Cash")) {
            driver.findElement(By.id("payment_method_cod")).click();
        }
    }

    public static void clickPlaceOrderButton() {
        WebDriver driver = DriverManager.getDriverInstance();
        driver.findElement(By.id("place_order")).click();
    }

    public static void verifyOrderDetailsSections() {
        WebDriver driver = DriverManager.getDriverInstance();
        if (driver.findElements(By.xpath("//h2[contains(text(),'Order Details')]")).isEmpty()) {
            throw new AssertionError("No se muestran los detalles del pedido.");
        }
    }

//    public static String getTaxText() {
//        WebDriver driver = DriverManager.getDriverInstance();
//        return driver.findElement(By.cssSelector(".tax-rate .amount")).getText();
//    }

    public static void verifyOnOrderConfirmationPage() {
        WebDriver driver = DriverManager.getDriverInstance();

        if (!driver.getCurrentUrl().contains("order-received")) {
            throw new AssertionError("No estás en la página de confirmación de pedido.");
        }
        System.out.println("Confirmado: estás en la orden final.");
    }

    public static void verifyTaxRateForLocation(int expectedTaxRate, String location) {
        WebDriver driver = DriverManager.getDriverInstance();

        WebElement taxElement = driver.findElement(By.cssSelector(".tax-rate .amount, .tax-rate"));
        String taxText = taxElement.getText().replaceAll("[^\\d]", "");

        int taxRateDisplayed;
        try {
            taxRateDisplayed = Integer.parseInt(taxText);
        } catch (NumberFormatException e) {
            throw new AssertionError("No se pudo parsear el impuesto mostrado: " + taxText);
        }

        if (taxRateDisplayed != expectedTaxRate) {
            throw new AssertionError("La tasa de impuesto para " + location + " es " + taxRateDisplayed + "% pero se esperaba " + expectedTaxRate + "%.");
        }
        System.out.println("Confirmado: tasa de " + expectedTaxRate + "% para " + location);
    }

    public static void clickAddToBasketButton(String buttonText) {
        WebDriver driver = DriverManager.getDriverInstance();

        // Primero ir al shop
        driver.findElement(By.linkText("Shop")).click();

        // Esperar que aparezca el button con el texto que reciben como argumento
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement addToBasketBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), '" + buttonText + "')]"))
        );
        addToBasketBtn.click();

        System.out.println("Libro agregado al carrito.");
    }
    public static void addFirstBookToBasketAndProceedToCheckout(String country) {
        WebDriver driver = DriverManager.getDriverInstance();

        // Primero ir al shop
        driver.findElement(By.linkText("Shop")).click();

        // Agregar al carrito el primer libro que aparece en la tienda
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement firstAddToBasket = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/ul/li[1]/a[2]"))
        );
        firstAddToBasket.click();

        // Ir al carrito
        WebElement viewCart = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='wpmenucartli']/a"))
        );
        viewCart.click();

        // Hacer proceed to checkout
        WebElement proceedToCheckout = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='page-34']/div/div[1]/div/div/div/a"))
        );
        proceedToCheckout.click();

        // Elegir el país en el combo
        WebElement countryDropDown = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='select2-chosen-1']"))
        );
        countryDropDown.click();

        // Esto despliega un buscador:
        WebElement searchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='search']"))
        );
        searchBox.sendKeys(country);
        searchBox.sendKeys(Keys.ENTER);
    }
    public static double getTaxRate()
    {
        WebDriver driver = DriverManager.getDriverInstance();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Busca cualquier <span> bajo #order_review que contenga '%'
        By iva = By.xpath("//*[@id='order_review']//span[contains(text(), '%')]");
        WebElement tax = wait.until(
                ExpectedConditions.presenceOfElementLocated(iva)
        );

        // Ejemplo de texto en el span: "(2%)"
        String taxText = tax.getText();

        Pattern pattern = Pattern.compile("\\((\\d+(\\.\\d+)?)%\\)");
        Matcher matcher = pattern.matcher(taxText);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1)); // Porcentaje de IVA
        }
        return 0;
    }


}