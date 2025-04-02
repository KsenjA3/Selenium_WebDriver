package tests.aqa;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

//        WebDriverManager.chromedriver().driverVersion("133.0.6943.127").setup();
//        System.setProperty("webdriver.chrome.driver", "c:\\Program Files\\SeleniumDrivers\\selenium-java-4.29.0\\selenium-chromium-driver-4.29.0.jar");

//        Шаги по настройке возможностей браузера:
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Start browser maximized
        options.addArguments("--headless"); // Run browser in headless mode
//        options.addArguments("--disable-gpu"); // Disable GPU acceleration

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://demowebshop.tricentis.com");
        System.out.println(driver.getTitle());

        //Взаимодействие с веб-элементами:
//        WebElement element = driver.findElement(By.id("element-id"));
//        element.click();

//        Шаги по настройке мультивозможностей браузера:
//        MultiCapabilities можно использовать для объединения различных наборов возможностей
//        в одном экземпляре WebDriver, что полезно для сложных тестовых настроек
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("browserName", "chrome");
//        capabilities.setCapability("version", "89.0");
//        capabilities.setCapability("platform", "Windows 10");
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//
//        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        driver.quit();
    }
}