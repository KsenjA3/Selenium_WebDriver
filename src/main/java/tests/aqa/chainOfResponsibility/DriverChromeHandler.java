package tests.aqa.chainOfResponsibility;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Log4j2
public class DriverChromeHandler implements DriverHandler{
    private DriverHandler nextHandler;

    @Override
    public void setNextHandler(DriverHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public WebDriver getDriver(String browser) {
        WebDriver driver=null;

        if (browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);
            driver.manage().window().setPosition(new Point(0,0));
            log.info("Perform driver from Chain of Responsibility" + browser);
        } else if (nextHandler != null) {
            driver =nextHandler.getDriver(browser);
        }
        return driver;
    }
}
