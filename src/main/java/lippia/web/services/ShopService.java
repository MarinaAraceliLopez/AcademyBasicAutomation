package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.ShopConstants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;

public class ShopService {

    public static void verifyPricesInRange(int min, int max) {
        List<WebElement> priceElements = WebActionManager.getElements(ShopConstants.PRODUCT_PRICES);
        for (WebElement element : priceElements) {
            String priceText = element.getText().trim();
            try {
                double price = Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
                if (price < min || price > max) {
                    throw new AssertionError("El precio " + price + " está fuera del rango (" + min + " - " + max + ")");
                }
                System.out.println("El precio " + price + " está dentro del rango.");
            } catch (NumberFormatException e) {
                throw new AssertionError("Error al analizar el precio: " + priceText);
            }
        }
    }


    public static void setPriceRange(int min, int max) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriverInstance();
        js.executeScript("document.getElementById('min_price').value = arguments[0];", min);
        js.executeScript("document.getElementById('max_price').value = arguments[0];", max);
        clickFilterButton();
    }

    public static void clickFilterButton() {
        WebElement filterButton = WebActionManager.getElement(ShopConstants.FILTER_BUTTON);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriverInstance();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", filterButton);


        {}

        filterButton.click();
    }

    public static void selectSortingOption(String option) {
        WebElement dropdown = WebActionManager.getElement(ShopConstants.SORT_DROPDOWN);
        Select select = new Select(dropdown);

        boolean found = select.getOptions().stream()
                .anyMatch(opt -> opt.getText().equalsIgnoreCase(option));

        if (!found) {
            throw new AssertionError("La opción de ordenamiento '" + option + "' no se encuentra en el dropdown.");
        }

        select.selectByVisibleText(option);
    }

    public static void verifyProductsVisible() {
        List<WebElement> products = WebActionManager.getElements(ShopConstants.PRODUCTS_LIST);
        if (products.isEmpty()) {
            throw new AssertionError("No se encontraron productos visibles.");
        }
        System.out.println("Se encontraron " + products.size() + " productos visibles.");
    }

    public static void clickSaleProduct(String position) {

        String productLocator = getSaleProductLocator(position);
        try {

            WebElement product = WebActionManager.getElement(productLocator);
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriverInstance();
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", product);
            try {
                product.click();
            } catch (Exception e) {
                System.out.println("Clic normal falló, intentando con JavaScript...");
                js.executeScript("arguments[0].click();", product);
            }
        } catch (NoSuchElementException e) {
            throw new AssertionError("No se encontró el producto en la posición: " + position);
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
        WebElement oldPrice = WebActionManager.getElement(ShopConstants.OLD_PRICE);
        WebElement newPrice = WebActionManager.getElement(ShopConstants.NEW_PRICE);

        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriverInstance();
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", oldPrice);

            if (!oldPrice.isDisplayed() || !newPrice.isDisplayed()) {
                throw new AssertionError("Uno o ambos precios no son visibles.");
            }

            System.out.println("Precio antiguo (tachado): " + oldPrice.getText());
            System.out.println("Precio nuevo: " + newPrice.getText());
        } catch (Exception e) {
            throw new AssertionError("Error al verificar los precios: " + e.getMessage());
        }
    }
}
