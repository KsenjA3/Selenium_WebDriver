package tests.aqa.factoryDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactoryEdge extends DriverFactory {
    @Override
    public WebDriver getDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("headless");
        return new EdgeDriver(options);
    }
}
