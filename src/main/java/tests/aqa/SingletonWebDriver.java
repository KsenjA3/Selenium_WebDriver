package tests.aqa;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Log4j2
public class SingletonWebDriver {
    private static volatile WebDriver driverChrome;
    private static volatile WebDriver driverEdge;
    private static volatile WebDriver driverMozilla;

    public static WebDriver getDriver(String browser) {
        log.info("Perform driver " + browser);
        WebDriver driver=chooseDriver(browser);
        if (driver==null) {
            synchronized (WebDriver.class) {
                if (driver == null) {

                    switch (browser) {
                        case "chrome"-> {
                            ChromeOptions options = new ChromeOptions();
                            options.addArguments("headless");
                            driver =driverChrome = new ChromeDriver(options);
                        }
                        case "edge"-> {
                            EdgeOptions options = new EdgeOptions();
                            options.addArguments("headless");
                            driver =driverEdge = new EdgeDriver(options);
                        }
                        case "mozilla"-> {
                            FirefoxOptions options = new FirefoxOptions();
                            options.addArguments("headless");
                            driver =driverMozilla = new FirefoxDriver(options);
                        }
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
            switch (browser) {
                case "chrome"-> driverChrome=null;
                case "edge"-> driverEdge=null;
                case "mozilla"-> driverMozilla=null;
            }
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
