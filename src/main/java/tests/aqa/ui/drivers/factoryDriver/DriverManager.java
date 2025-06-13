package tests.aqa.ui.drivers.factoryDriver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DriverManager {
    public WebDriver createDriver(String browser) {
        DriverFactory driver;
        switch (browser) {
            case "Chrome"-> driver = new DriverFactoryChrome();
            case "Firefox"->  driver = new DriverFactoryMozilla();
            case "Edge"->  driver = new DriverFactoryEdge();
            case "Safari"->  driver = new DriverFactorySafari();
            default -> {
                log.error("Unknown browser " + browser);
                driver = null;
            }
        }
        log.info("Perform driver from Abstract Factory" + browser);
        return driver.getDriver();
    }
}
