package tests.aqa.factoryDriver;

import org.openqa.selenium.WebDriver;

public abstract class DriverFactory {
    private  WebDriver driver;
    public abstract WebDriver getDriver();
}
