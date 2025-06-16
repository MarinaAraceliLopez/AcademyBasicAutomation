package lippia.web.hooks;

import com.crowdar.driver.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.core.api.Scenario;
import lippia.web.listeners.DriverValidatorListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Basic {

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        if (DriverManager.getDriverInstance() == null) {
            throw new RuntimeException("WebDriver no iniciado.");
        }
        DriverManager.getDriverInstance()
                .register(new DriverValidatorListener());

        DriverManager.getDriverInstance()
                .manage()
                .deleteAllCookies();

        System.out.println("Cookies limpiadas antes de cada escenario.");
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                File screenshot = ((TakesScreenshot) DriverManager.getDriverInstance()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("target/screenshots/" + scenario.getName() + ".png"));
                System.out.println("Captura tomada porque falló el escenario: " + scenario.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finalizó el escenario: " + scenario.getName());
    }
}
