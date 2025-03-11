package tests.aqa.ui.drivers.singleton;

//public enum Browser {
//    FIREFOX,
//    CHROME,
//    EDGE
//}
//
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.function.Supplier;

@AllArgsConstructor
@Getter
public enum Browser {
    CHROME(
            "Chrome",
                    () -> {
                        var options = new ChromeOptions();
                        options.addArguments("headless");
                        return new ChromeDriver(options);
                    }
    ),
    EDGE(
            "Edge",
            () -> {
                var options = new EdgeOptions();
                options.addArguments("headless");
                return new EdgeDriver(options);
            }
    ),
    MOZILLA(
            "Firefox",
            () -> {
                var options = new FirefoxOptions();
                options.addArguments("-headless");
                return new FirefoxDriver( options);
            }
    ),
    ;

    private  final String browserName;
    private  final Supplier<WebDriver> driver;

    public WebDriver createDriver() {

        return driver.get();
    }


}