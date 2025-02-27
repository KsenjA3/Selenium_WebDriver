package tests.aqa;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

@Log4j2
public class SingletonWebDriver {
    private static volatile WebDriver driverChrome;
    private static volatile WebDriver driverEdge;
    private static volatile WebDriver driverMozilla;

    public static WebDriver getDriver(String browser) {
        log.info("Perform driver " + browser);
        WebDriver driver=chooseDriver(browser);

        if (driver == null) {
            synchronized (WebDriver.class) {
                if (driver == null) {
                    switch (browser) {
                        case "chrome"-> driver =driverChrome = new ChromeDriver();
                        case "edge"-> driver =driverEdge = new EdgeDriver();
                        case "mozilla"-> driver =driverMozilla = new ChromeDriver();
                    }
                    driver.manage().window().maximize();
                }
            }
        }
        return driver;
    }

    public static void quitDriver(String browser) {
        WebDriver driver=chooseDriver(browser);
        if (driver != null) {
            driver.quit();
        }
        log.info("Driver is closed");
    }

    private static WebDriver chooseDriver(String browser) {
        WebDriver driver;
        switch (browser) {
            case "chrome"-> driver = driverChrome;
            case "edge"-> driver =driverEdge;
            case "mozilla"-> driver =driverMozilla;
            default -> driver = null;
        }
        return driver;
    }

}
