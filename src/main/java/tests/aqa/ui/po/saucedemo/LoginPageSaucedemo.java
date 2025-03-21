package tests.aqa.ui.po.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.utils.ConfProperties;

import java.time.Duration;

public class LoginPageSaucedemo {
    private WebDriver driver;

    public LoginPageSaucedemo (WebDriver driver) {
        this.driver = driver;
        driver.get(ConfProperties.getProperty("saucedemo_login_page"));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public void login () {
        String username = ConfProperties.getProperty("saucedemo_usermane");
        String password = ConfProperties.getProperty("saucedemo_password");

        this.username.sendKeys(username);
        this.password.sendKeys(password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(this.loginButton)).click();
    }
}
