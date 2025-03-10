package tests.aqa.ui.drivers.singleton;

public enum Browser {
    FIREFOX,
    CHROME,
    EDGE
}

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//@AllArgsConstructor
//@Getter
//public enum Browser {
//    CHROME(
//            "chrome",
//            true,
//                    () -> {
//                        var options = new ChromeOptions();
//                        options.addArguments("headless");
//                        return options;
//                    },
//                    () -> new ChromeDriver(Browser.())),
//
//    EDGE(""),
//    MOZILLA
//    ;
//
//    private final String browserName;
//    private final Boolean headless;
//    private final ChromeOptions options;
//    private final WebDriver driver;
//
//
//}