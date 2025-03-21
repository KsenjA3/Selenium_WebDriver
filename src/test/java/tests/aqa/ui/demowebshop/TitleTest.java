package tests.aqa.ui.demowebshop;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.aqa.utils.ConfProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitleTest{
    WebDriver driver;

    @BeforeTest
    void setUp() {
        driver = new ChromeDriver();
        driver.get(ConfProperties.getProperty("shop_page"));
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }

    @Test
    void title() {
        assertEquals("Demo Web Shop",driver.getTitle());
    }

    @Test
    void failTest1() {
        assertEquals(1,2);
    }
    @Test
    void failTest2() {
        assertEquals(3,2);
    }
}
