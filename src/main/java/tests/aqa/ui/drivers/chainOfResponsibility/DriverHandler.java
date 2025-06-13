package tests.aqa.ui.drivers.chainOfResponsibility;

import org.openqa.selenium.WebDriver;

public interface DriverHandler {
      WebDriver getDriver(String browser);
      void setNextHandler(DriverHandler nextHandler);
}
