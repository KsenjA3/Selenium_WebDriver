package tests.aqa.factoryDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactoryMozilla extends DriverFactory {
    @Override
    public WebDriver getDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        return new FirefoxDriver(options);
    }
}
