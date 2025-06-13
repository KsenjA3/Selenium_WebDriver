package tests.aqa.ui.drivers.chainOfResponsibility;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Log4j2
public class DriverFirefoxHandler implements DriverHandler{
    private DriverHandler nextHandler;

    @Override
    public void setNextHandler(DriverHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public WebDriver getDriver(String browser) {
        WebDriver driver=null;

        if (browser.equalsIgnoreCase("Firefox")) {
//            System.setProperty("webdriver.gecko.driver", "c:/Program Files/SeleniumDrivers/selenium-java-4.29.0/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            driver = new FirefoxDriver(options);

            driver.manage().window().setPosition(new Point(200,200));
            log.info("Perform driver from Chain of Responsibility" + browser);

        } else if (nextHandler != null) {
            driver =nextHandler.getDriver(browser);
        }
        return driver;
    }
}
