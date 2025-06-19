package lippia.web.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class LocatorValidator {

    public static void main(String[] args) {
        // Configuración del WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        // URL de la página donde probar los locators
        driver.get("https://practice.automationtesting.in/");

        // Definición de locators desde las constantes
        Map<String, String> locators = new HashMap<>();

        // LoginConstants
        locators.put("USERNAME_EMAIL_FIELD", "xpath://input[@id='username']");
        locators.put("LOGIN_PASSWORD_FIELD", "xpath://input[@id='password']");
        locators.put("LOGIN_BUTTON", "xpath://*[@id='customer_login']//form//input[@type='submit' and @value='Login']");
        locators.put("ERROR_MESSAGE", "xpath://ul[contains(@class,'woocommerce-error')]/li");
        locators.put("SUCCESS_CONTENT", "xpath://div[contains(@class,'woocommerce-MyAccount-content')]");
        locators.put("LOGOUT_BUTTON", "xpath://a[text()='Logout']");
        locators.put("MY_ACCOUNT_MENU", "xpath://nav//a[contains(text(),'My Account')]");

        // MyAccountConstants
        locators.put("WELCOME_MESSAGE", "xpath://div[contains(@class,'woocommerce-MyAccount-content')]//p[contains(text(),'Hello')]");
        locators.put("ACCOUNT_DETAILS_BUTTON", "xpath://a[text()='Account Details']");
        locators.put("ADDRESS_BUTTON", "xpath://a[text()='Addresses']");
        locators.put("ORDERS_BUTTON", "xpath://a[text()='Orders']");
        locators.put("EDIT_SHIPPING_ADDRESS_BUTTON", "xpath://h3[text()='Shipping Address']/following-sibling::a[text()='Edit']");
        locators.put("SHIPPING_FORM", "xpath://form[@action='https://practice.automationtesting.in/my-account/edit-address/shipping/']");

        // RegistrationConstants
        locators.put("REGISTRATION_EMAIL", "xpath://*[@id='reg_email']");
        locators.put("REGISTRATION_PASSWORD", "xpath://*[@id='reg_password']");
        locators.put("REGISTER_BUTTON", "xpath://*[@id='customer_login']/div[2]/form/p[3]/input[3]");
        locators.put("SUCCESS_CONTENT", "css:.woocommerce-MyAccount-content");
        locators.put("REGISTRATION_FORM", "xpath://form[contains(@class,'register')]");

        // Validar los locators
        validateLocators(driver, locators);

        // Cerrar el navegador
        driver.quit();
    }

    private static void validateLocators(WebDriver driver, Map<String, String> locators) {
        System.out.println("Iniciando la validación de locators...");
        for (Map.Entry<String, String> entry : locators.entrySet()) {
            String name = entry.getKey();
            String locator = entry.getValue();

            try {
                // Identificar el tipo de locator (XPath o CSS)
                By by = locator.startsWith("xpath:") ?
                        By.xpath(locator.replace("xpath:", "")) :
                        By.cssSelector(locator.replace("css:", ""));

                // Intentar encontrar el elemento
                WebElement element = driver.findElement(by);

                // Validar si el elemento es visible
                if (element.isDisplayed()) {
                    System.out.println("✔ Locator válido: " + name);
                }
            } catch (Exception e) {
                System.out.println("✘ Error en locator: " + name + " - " + e.getMessage());
            }
        }
        System.out.println("Validación completa.");
    }
}
