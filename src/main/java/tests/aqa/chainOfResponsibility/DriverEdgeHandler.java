package tests.aqa.chainOfResponsibility;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

@Log4j2
public class DriverEdgeHandler implements DriverHandler{
    private DriverHandler nextHandler;

    @Override
    public void setNextHandler(DriverHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public WebDriver getDriver(String browser) {
        WebDriver driver=null;

        if (browser.equalsIgnoreCase("Edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("headless");
            driver = new EdgeDriver(options);
            driver.manage().window().setPosition(new Point(400,400));
            log.info("Perform driver from Chain of Responsibility" + browser);

        } else if (nextHandler != null) {
            driver =nextHandler.getDriver(browser);
        }
        return driver;
    }
}
