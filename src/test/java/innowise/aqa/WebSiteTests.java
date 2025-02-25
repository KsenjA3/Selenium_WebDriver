package innowise.aqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebSiteTests {
    WebDriver driver;


    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get(ConfProperties.getProperty("testingpage"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void title() {
        assertEquals("Demo Web Shop",driver.getTitle());
    }
}
