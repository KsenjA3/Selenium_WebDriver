package tests.aqa.ui.drivers.singleton;

public enum Browser {
    FIREFOX,
    CHROME,
    EDGE
}

//public enum Browser {
//    CHROME("chrome", true, () -> {
//        var options = new ChromeOptions();
//        options.addArguments("headless");
//        return options;
//    }, () -> new ChromeDriver(Browser.getOptions())),
//    EDGE(""),
//    MOZILLA;
//
//    private String browserName;
//    private Boolean headless;
//    private static Options options;
//    private WebDriver driver;
//
//    public static Options getOptions() {
//        return options;
//    }
//}