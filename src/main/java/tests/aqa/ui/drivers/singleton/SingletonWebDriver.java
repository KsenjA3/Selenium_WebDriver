package tests.aqa.ui.drivers.singleton;

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
    private static volatile WebDriver driver;

    public static WebDriver getDriver(String browser) {
        log.info("Perform driver " + browser);
        if (driver==null) {
            synchronized (WebDriver.class) {
                if (driver == null) {

                    switch (browser) {
                        case "Chrome"-> {
                            ChromeOptions options = new ChromeOptions();
                            options.addArguments("headless");
                            driver =  new ChromeDriver(options);
                        }
                        case "Edge"-> {
                            EdgeOptions options = new EdgeOptions();
                            options.addArguments("headless");
                            driver = new EdgeDriver(options);
                        }
                        case "Firefox"-> {
                            FirefoxOptions options = new FirefoxOptions();
                            options.addArguments("-headless");
                            driver = new FirefoxDriver(options);
                        }
                    }
                    driver.manage().window().maximize();
                }
            }
        }
        return driver;
    }

    public static void quitDriver(String browser) {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        log.info("Driver is closed");
    }
}
