package lippia.web.services;

import org.openqa.selenium.By;

public class LocatorService {

    public static By getBy(String locatorString) {
        if (locatorString.startsWith("xpath:")) {
            String xpath = locatorString.replace("xpath:", "");
            return By.xpath(xpath);
        } else if (locatorString.startsWith("css:")) {
            String css = locatorString.replace("css:", "");
            return By.cssSelector(css);
        } else if (locatorString.startsWith("id:")) {
            String id = locatorString.replace("id:", "");
            return By.id(id);
        }
        // Agregás más si usás otros tipos de localizadores
        else {
            throw new IllegalArgumentException("Tipo de locator no soportado: " + locatorString);
        }
    }
}
