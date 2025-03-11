package tests.aqa.ui;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import tests.aqa.ConfProperties;
import tests.aqa.ui.drivers.chainOfResponsibility.DriverChromeHandler;
import tests.aqa.ui.drivers.chainOfResponsibility.DriverEdgeHandler;
import tests.aqa.ui.drivers.chainOfResponsibility.DriverFirefoxHandler;
import tests.aqa.ui.drivers.chainOfResponsibility.DriverHandler;
import tests.aqa.ui.drivers.singleton.Browser;
import tests.aqa.ui.drivers.singleton.SingletonWebDriver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected  WebDriver driver;
    protected Set<WebDriver> driverSet;
    private String[] browserList;


    @BeforeEach
    void setUp() {
        /**
         *  Получаем список браузеров из системных свойств,
         *  подаваемых через файл conf.yaml переменную drivers,
         *  в которой названия браузеров представлены виде строки
         *  и перечисляются через запятую         *
         */
        String browsers = System.getProperty("browsers", ConfProperties.getProperty("drivers"));
        browserList = browsers.split(",");
        driverSet = new HashSet<WebDriver>();

        /**
         *                      I СПОСОБ
         * Запуск тестов осуществляется для каждого браузера из списка,
         * через паттерн Abstract Factory
         * выбрасывает ошибку (которая видна сразу) и тесты не будут выполняться
         * при неверном названии одного из браузеров, указанного в conf.yaml         *
         */
//        for (String browserName : browserList) {
//            DriverManager driverManager = new DriverManager();
//            driver = driverManager.createDriver(browserName);
//            driverSet.add(driver);
//        }

        /**
         *                      II СПОСОБ
         * Запуск тестов осуществляется для каждого браузера из списка,
         * через паттерн Chain of Responsibility
         * преимущества: при ошибке в названии одного из браузеров, указанного в conf.yaml
         * тесты будут выполняться на тех, что указаны верно
         * недостатки: если не читать Log, то можно не заметить,
         * что не на всех браузерах из списка произведены тесты
         */
        DriverHandler handlerDriver = new DriverChromeHandler();
        DriverHandler driverFirefox = new DriverFirefoxHandler();
        DriverHandler driverEdge = new DriverEdgeHandler();
        handlerDriver.setNextHandler( driverFirefox);
        driverFirefox.setNextHandler(driverEdge);
        for (String browserName : browserList) {
            driver = handlerDriver.getDriver(browserName);
            if (driver != null)  driverSet.add(driver);
        }

        /**
         *                     III СПОСОБ
         * Запуск тестов осуществляется только для одного браузера,
         * из enum singleton.Browser
         * который явно указывается в BaseTest
         * при вызове SingletonWebDriver.getDriver(Browser.имя_браузера)
         * через паттерн Singleton
         */
//        driverSet.add( SingletonWebDriver.getDriver(ConfProperties.getProperty("drivers")));

        log.info("Driver set: " + driverSet);
    }

    @AfterEach
    void tearDown() {
        /**         для I и II способа
         * Закрытие драйвера и очистка ресурсов после выполнения всех тестов
         * при запуске UI тестов с использованием паттернов
         * Chain of Responsibility и Abstract Factory
          */
        driverSet.forEach(WebDriver::quit);

        /**         для III способа
         * Закрытие драйвера и очистка ресурсов после выполнения всех тестов
         * при запуске UI тестов с использованием паттерна
         * Chain of Responsibility и Abstract Factory
         */
//        SingletonWebDriver.quitDriver(ConfProperties.getProperty("drivers"));

        log.info("Drivers are closed");
    }
}
