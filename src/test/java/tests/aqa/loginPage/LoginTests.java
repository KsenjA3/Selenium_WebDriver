package tests.aqa.loginPage;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import tests.aqa.ConfProperties;
import tests.aqa.po.LoginPage;

@Log4j2

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginTests extends BaseTest{
    LoginPage loginPage;

    @BeforeAll
     void openPage()  {
        log.info("The site login page " +ConfProperties.getProperty("log_page")+ " is opened");
        driver.get(ConfProperties.getProperty("log_page"));
        loginPage=new LoginPage(driver);
    }
}
