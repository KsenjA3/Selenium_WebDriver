package tests.aqa.loginPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.aqa.ConfProperties;
import tests.aqa.SingletonWebDriver;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void setUpDriver() {
        driver = SingletonWebDriver.getDriver(ConfProperties.getProperty("driver"));
        driver.get(ConfProperties.getProperty("log_page"));
    }


    @AfterEach
    void shutDownDriver() {
        SingletonWebDriver.quitDriver(ConfProperties.getProperty("driver"));
    }
}
