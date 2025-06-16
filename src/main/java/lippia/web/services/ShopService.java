package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.ShopConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;


public class ShopService {


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
        WebElement filterButton = driver.findElement(By.cssSelector("button[type='submit']"));
        filterButton.click();
    }


    public static void verifyPricesInRange(int min, int max) {
        List<WebElement> priceContainers = DriverManager.getDriverInstance()
                .findElements(By.cssSelector(".products li .price"));

        System.out.println("Productos encontrados: " + priceContainers.size());

        for (WebElement priceContainer : priceContainers) {
            String priceText = "";


            if (priceContainer.findElements(By.tagName("ins")).size() > 0) {
                priceText = priceContainer.findElement(By.tagName("ins")).getText();
            } else {

                priceText = priceContainer.getText();
            }

            priceText = priceText.replaceAll("[^\\d.]", "");

            try {
                double price = Double.parseDouble(priceText);
                System.out.println("Precio encontrado: " + price);

                if (price < min || price > max) {
                    throw new AssertionError("El precio " + price + " está fuera del rango (" + min + " - " + max + ")");
                }
            } catch (NumberFormatException e) {
                throw new AssertionError("Error al analizar el precio: " + priceText);
            }
        }

    }


    public static void selectSortingOption(String option) {
        WebElement dropdown = DriverManager.getDriverInstance().findElement(ShopConstants.SORT_DROPDOWN);
        Select select = new Select(dropdown);

        boolean found = select.getOptions().stream()
                .anyMatch(opt -> opt.getText().equalsIgnoreCase(option));

        if (!found) {
            throw new AssertionError("La opción de ordenamiento '" + option + "' no se encuentra en el dropdown.");
        }

        select.selectByVisibleText(option);
    }

    public static void verifyProductsVisible() {
        List<WebElement> products = DriverManager.getDriverInstance().findElements(ShopConstants.PRODUCTS_LIST);
        if (products.isEmpty()) {
            throw new AssertionError("No se encontraron productos visibles.");
        }
        System.out.println("Se encontraron " + products.size() + " productos visibles.");
    }

    public static void clickSaleProduct(String position) {
        String productLocator = getSaleProductLocator(position);
        WebDriver driver = DriverManager.getDriverInstance();

        try {
            WebElement product = WebActionManager.getElement(productLocator);

            Thread.sleep(1500);
            try {
                List<WebElement> ads = driver.findElements(By.id("aswift_6_host"));
                if (!ads.isEmpty() && ads.get(0).isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", ads.get(0));
                    System.out.println("Anuncio ocultado con JavaScript.");
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                System.out.println("No se encontró el anuncio o no se pudo ocultar. Continuando...");
            }
            product = WebActionManager.getElement(productLocator);


            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError("Thread sleep fue interrumpido");
        } catch (Exception e) {
            throw new AssertionError("Error al intentar hacer clic en el producto: " + e.getMessage());
        }
    }




    private static String getSaleProductLocator(String position) {
        switch (position.toLowerCase()) {
            case "first":
                return ShopConstants.SALE_PRODUCT_FIRST;
            case "second":
                return ShopConstants.SALE_PRODUCT_SECOND;
            default:
                throw new IllegalArgumentException("Posición no soportada: " + position);
        }
    }

    public static void verifyPriceWithStrikeThrough() {
        WebDriver driver = DriverManager.getDriverInstance();

        WebElement oldPrice = driver.findElement(ShopConstants.OLD_PRICE);
        WebElement newPrice = driver.findElement(ShopConstants.NEW_PRICE);

        if (!oldPrice.isDisplayed() || !newPrice.isDisplayed()) {
            throw new AssertionError("Uno o ambos precios no son visibles.");
        }


        WebElement delElement = oldPrice.findElement(By.xpath(".."));
        if (!delElement.getTagName().equalsIgnoreCase("del")) {
            throw new AssertionError("El precio antiguo no está dentro de una etiqueta <del>.");
        }

        System.out.println("Precio antiguo (tachado): " + oldPrice.getText());
        System.out.println("Precio nuevo: " + newPrice.getText());
    }


    public static void clickAddToBasketButton(String buttonText) {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement button = driver.findElement(By.xpath("//a[contains(text(),'" + buttonText + "') or contains(@class,'add_to_cart_button')]"));
        button.click();
    }

    public static void verifyBookInBasketWithPrice() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement basketMenu = driver.findElement(By.cssSelector(".cartcontents"));
        if (!basketMenu.isDisplayed()) {
            throw new AssertionError("El libro no está visible en el menú del carrito.");
        }
        String text = basketMenu.getText();
        if (!text.matches(".*\\d+\\.\\d{2}.*")) {
            throw new AssertionError("No se muestra precio junto al libro en el carrito.");
        }
    }

    public static void clickBasketMenuLink() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement basketLink = driver.findElement(By.cssSelector(".wpmenucart-contents"));
        basketLink.click();
    }

    public static void verifyOnCheckoutPage() {
        WebDriver driver = DriverManager.getDriverInstance();
        if (!driver.getCurrentUrl().contains("checkout")) {
            throw new AssertionError("No se encuentra en la página de checkout.");
        }
    }

    public static void verifyTotalAndSubtotalVisible(String buttonText) {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement subtotal = driver.findElement(By.cssSelector(".cart-subtotal .amount"));
        WebElement total = driver.findElement(By.cssSelector(".order-total .amount"));
        WebElement proceedButton = driver.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]"));

        if (!subtotal.isDisplayed() || !total.isDisplayed() || !proceedButton.isDisplayed()) {
            throw new AssertionError("Subtotal, total o botón '" + buttonText + "' no están visibles.");
        }
    }

    public static void verifyTotalLessThanSubtotal() {
        WebDriver driver = DriverManager.getDriverInstance();

        String subtotalText = driver.findElement(By.cssSelector(".cart-subtotal .amount")).getText().replaceAll("[^\\d.]", "");
        String totalText = driver.findElement(By.cssSelector(".order-total .amount")).getText().replaceAll("[^\\d.]", "");

        double subtotal = Double.parseDouble(subtotalText);
        double total = Double.parseDouble(totalText);

        if (!(total < subtotal)) {
            throw new AssertionError("El total (" + total + ") NO es menor que el subtotal (" + subtotal + ").");
        }
    }

    public static void clickButtonByText(String buttonText) {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]"));
        button.click();
    }

    public static void verifyOnPaymentGatewayPage() {
        WebDriver driver = DriverManager.getDriverInstance();
        String url = driver.getCurrentUrl();
        if (!url.contains("checkout")) {
            throw new AssertionError("No está en la página de pasarela de pago.");
        }
    }

    public static void verifyDetailsSectionsVisible() {
        WebDriver driver = DriverManager.getDriverInstance();
        String[] selectors = {
                "#billing_address", // Billing Details
                ".woocommerce-checkout-review-order-table", // Order Details
                "#order_comments", // Additional details
                "#payment" // Payment gateway
        };
        for (String selector : selectors) {
            WebElement elem = driver.findElement(By.cssSelector(selector));
            if (!elem.isDisplayed()) {
                throw new AssertionError("El detalle '" + selector + "' no está visible.");
            }
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

        // Selección país, por ejemplo India
        Select countrySelect = new Select(driver.findElement(By.id("billing_country")));
        countrySelect.selectByVisibleText("India");
    }

    public static void selectPaymentMethod(String paymentMethod) {
        WebDriver driver = DriverManager.getDriverInstance();
        String paymentId;

        switch (paymentMethod.toLowerCase()) {
            case "direct bank transfer":
                paymentId = "payment_method_bacs";
                break;
            case "cheque":
                paymentId = "payment_method_cheque";
                break;
            case "cash":
                paymentId = "payment_method_cod";
                break;
            case "paypal":
                paymentId = "payment_method_paypal";
                break;
            default:
                throw new IllegalArgumentException("Método de pago no soportado: " + paymentMethod);
        }
        WebElement radioBtn = driver.findElement(By.id(paymentId));
        if (!radioBtn.isSelected()) {
            radioBtn.click();
        }
    }

    public static void clickPlaceOrderButton() {
        WebDriver driver = DriverManager.getDriverInstance();
        WebElement placeOrderBtn = driver.findElement(By.id("place_order"));
        placeOrderBtn.click();
    }

    public static void verifyOnOrderConfirmationPage() {
        WebDriver driver = DriverManager.getDriverInstance();
        if (!driver.getCurrentUrl().contains("order-received")) {
            throw new AssertionError("No está en la página de confirmación de orden.");
        }
    }

    public static void verifyOrderDetailsSections() {
        WebDriver driver = DriverManager.getDriverInstance();
        String[] selectors = {
                ".woocommerce-order-overview", // Order details
                ".woocommerce-bank-details", // Bank details
                ".woocommerce-customer-details", // Customer details
                "#billing_address" // Billing details
        };
        for (String selector : selectors) {
            WebElement elem = driver.findElement(By.cssSelector(selector));
            if (!elem.isDisplayed()) {
                throw new AssertionError("El detalle '" + selector + "' no está visible en la página de confirmación.");
            }
        }
    }

    public static void verifyTaxRateForLocation(int expectedTaxRate, String location) {
        WebDriver driver = DriverManager.getDriverInstance();
        // Aquí se debe obtener el valor de impuesto mostrado en checkout y validar la tasa

        WebElement taxElement = driver.findElement(By.cssSelector(".tax-rate .amount, .tax-rate"));

        String taxText = taxElement.getText().replaceAll("[^\\d]", "");

        int taxRateDisplayed;
        try {
            taxRateDisplayed = Integer.parseInt(taxText);
        } catch (NumberFormatException e) {
            throw new AssertionError("No se pudo parsear el impuesto mostrado: " + taxText);
        }

        if (taxRateDisplayed != expectedTaxRate) {
            throw new AssertionError("La tasa de impuesto para " + location + " es " + taxRateDisplayed + "% pero se esperaba " + expectedTaxRate + "%");
        }
    }

    public static String getTaxText() {
        // Aquí defines el locator del texto donde aparece el impuesto en checkout
        return WebActionManager.getText(ShopConstants.TAX_LABEL);
    }
}

