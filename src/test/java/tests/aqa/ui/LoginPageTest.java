package tests.aqa.ui;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import tests.aqa.BaseTest;
import tests.aqa.ConfProperties;
import tests.aqa.po.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginPageTest extends BaseTest {
    LoginPage loginPage;

    @BeforeEach
    void openPage()  {
        driver.get(ConfProperties.getProperty("log_page"));
        loginPage=new LoginPage(driver);
        log.info("The site login page " + driver.getCurrentUrl()+ " is opened");
    }

    @Test
    void testLoginPageTitle() {
        assertEquals("Вход на сайт",driver.getTitle());
    }

    @Test
    void testLoginPage_NameLabelCompany() {
        assertEquals("Aspect Language Centre",loginPage.getNameLabelCompany());
    }

    @Test
    void testLoginPage_DisplayedLabelCompany  () {
        assertTrue(loginPage.isDisplayed_LabelCompany());
    }

    @Test
    void testLoginPage_FieldLogin_Attribute_data_val_required() {
        assertEquals("Обязательное поле 'Логин'.",loginPage.getAttribute_FieldLogin_data_val_required());
    }

    @Test
    void testLoginPage_LoginButton_Color() {
        assertEquals("rgba(255, 255, 255, 1)",loginPage.getColor_LoginButton());
    }

    @Test
    void testLoginPage_clickHref_ForgotPassword() {
        loginPage.click_forgotPassword();
        assertEquals("https://aspect.t8s.ru/Account/ResetPassword",driver.getCurrentUrl());
    }

    @Test
    void testLoginPage_entry_withCorrectIdentity() {
        assertEquals("https://aspect.t8s.ru/Student",loginPage.loginPage("xlyna@yandex.ru", "FVqHMbtBfnQk"));

    }

    @Test
    void testLoginPage_entry_withIncorrectIdentity() {
        loginPage.loginPage("12345@yandex.ru", "123456789");
        assertEquals("https://aspect.t8s.ru/",driver.getCurrentUrl());
    }


@Test
    void testLoginPage_entry_withIncorrectIdentity_ReportError() {

        assertEquals("Неудачная попытка входа. Пожалуйста, попробуйте ещё раз.",
                loginPage.loginPage("12345@yandex.ru", "123456789"));
    }

}
