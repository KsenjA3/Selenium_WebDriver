package tests.aqa.ui;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.ui.po.HomePageLocator;
import tests.aqa.ui.po.LoginPage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class LoginPageTest extends BaseTest {
    List<LoginPage> loginPages;

    @BeforeEach
    void openPage()  {
        loginPages = new ArrayList<>();
        driverSet.forEach(driver -> {
            if (driver.getCurrentUrl().equals("https://aspect.t8s.ru/Student")) {
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HomePageLocator.EXIT_MENU_ACCOUNT_LOCATOR.getLocator())))
                        .click();
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HomePageLocator.EXIT_ACCOUNT_LOCATOR.getLocator())))
                        .click();
            }
            loginPages.add(new LoginPage(driver));
            log.info("The site login page " + driver.getCurrentUrl()+ " is opened with driver " + driver);
        });
        log.info("The login page " + loginPages);
    }
    private Stream<LoginPage> provideLoginPages() {
        return loginPages.stream();
    }

    @Test
    void testLoginPageTitle() {
        loginPages.forEach(loginPage -> {
            assertEquals("Вход на сайт",loginPage.getTitle());
        });
    }

    @Test
    void testLoginPageNameLabelCompany() {
        loginPages.forEach(loginPage -> {
            assertEquals("Aspect Language Centre",loginPage.getNameLabelCompany());
        });
    }

    @Test
    void testLoginPageDisplayedLabelCompany  () {
        loginPages.forEach(loginPage -> {
            assertTrue(loginPage.isDisplayedLabelCompany());
        });
    }

    @Test
    void testLoginPageFieldLoginAttributeDataValRequired() {
        loginPages.forEach(loginPage -> {
            assertEquals("Обязательное поле 'Логин'.",loginPage.getAttributeFieldLoginDataValRequired());
        });
    }

    @Test
    void testLoginPageLoginButtonColor() {
        loginPages.forEach(loginPage -> {
            assertEquals("rgb(255, 255, 255)",loginPage.getColorLoginButton());
        });
    }

    @Test
    void testLoginPageClickHrefForgotPassword() throws InterruptedException {
        loginPages.forEach(loginPage -> {
            assertEquals("https://aspect.t8s.ru/Account/ResetPassword",  loginPage.clickForgotPassword());
        });
    }

    @Test
    void testLoginPageEntryWithCorrectIdentity() {
        loginPages.forEach(loginPage -> {
            assertEquals("https://aspect.t8s.ru/Student",loginPage.tryLoginPage("xlyna@yandex.ru", "FVqHMbtBfnQk"));
        });
    }

    @Test
    void testLoginPageEntryWithIncorrectIdentity() {
        loginPages.forEach(loginPage -> {
            assertEquals("https://aspect.t8s.ru/", loginPage.tryLoginPage("12345@yandex.ru", "123456789"));
        });
    }

    @Test
    void testLoginPageEntryWithIncorrectIdentityReportError() {
        loginPages.forEach(loginPage -> {
            assertEquals("Неудачная попытка входа. Пожалуйста, попробуйте ещё раз.",
                    loginPage.tryLoginPageReturnInfoMessageOfResult("12345@yandex.ru", "123456789"));
        });
    }
}
