package tests.aqa.loginPage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import tests.aqa.ConfProperties;
import tests.aqa.SingletonWebDriver;



class BaseTest {

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
