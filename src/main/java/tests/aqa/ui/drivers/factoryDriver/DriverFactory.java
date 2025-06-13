package tests.aqa.ui.drivers.factoryDriver;

import org.openqa.selenium.WebDriver;

public abstract class DriverFactory {
    private  WebDriver driver;
    public abstract WebDriver getDriver();
}
