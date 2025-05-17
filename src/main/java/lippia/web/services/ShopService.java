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

}

