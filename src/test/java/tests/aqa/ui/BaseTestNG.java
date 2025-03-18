package tests.aqa.ui;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import tests.aqa.ui.drivers.factoryDriver.DriverManager;

@Log4j2
public abstract class BaseTestNG {
    protected WebDriver driver;
    private DriverManager driverManager;

    @BeforeClass
    public void setUp (){
        driverManager = new DriverManager();
        log.info("DriverManager is created" + driverManager);
    }


    @Parameters({"browser"})
    @BeforeMethod
    public void setDriver(String browser) {
        driver = driverManager.createDriver(browser);
        log.info("Driver is created " + driver);
    }

    @AfterMethod
    public void tearDownDriver() {
        driver.quit();
        log.info("Driver is closed." );
    }
}
