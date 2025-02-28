package tests.aqa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;


public class BaseTest {

    protected static WebDriver driver;

    @BeforeAll
    static void setUpDriver() {
        driver = SingletonWebDriver.getDriver(ConfProperties.getProperty("driver"));
    }

    static @AfterAll
    void shutDownDriver() {
        SingletonWebDriver.quitDriver(ConfProperties.getProperty("driver"));
    }
}
