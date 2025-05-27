package tests.aqa.ui.drivers.factoryDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactorySafari extends DriverFactory {
    @Override
    public WebDriver getDriver() {
        System.setProperty("webdriver.safari.driver", "path/to/safariDriver.safariextz"); // Replace with your SafariDriver path

        return new SafariDriver();
    }

}
