package tests.aqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TitleTest{
    WebDriver driver;


    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get(ConfProperties.getProperty("shop_page"));
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
