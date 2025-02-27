package tests.aqa.po;

import lombok.extern.log4j.Log4j2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.ConfProperties;

import java.time.Duration;

@Log4j2
public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        driver.get(ConfProperties.getProperty("log_page"));
    }

    //Set user name in textbox
    public void setUserName(String strUserName){
        log.info("set user name to " + strUserName);
        driver.findElement(By.xpath(LoginPageLocator.USERNAME_INPUT_LOCATOR.get())).sendKeys(strUserName);
    }

    //Set password in password textbox
    public void setPassword(String strPassword){
        log.info("set password to " + strPassword);
        driver.findElement(By.xpath(LoginPageLocator.PASSWORD_INPUT_LOCATOR.get())).sendKeys(strPassword);
    }

    //Click on login button
    public void clickLogin(){
        log.info("click login");
        driver.findElement(By.xpath(LoginPageLocator.LOGIN_BUTTON_LOCATOR.get())).click();
    }

    /**
     * This POM method will be exposed in test case to login in the application
     * @param strUserName
     * @param strPasword
     * @return
     */
    public void loginPage(String strUserName,String strPasword){
        //Fill user name
        this.setUserName(strUserName);
        //Fill password
        this.setPassword(strPasword);
        //Click Login button
        this.clickLogin();
    }

    public String getLabelSite() {
        log.info("Text on the Login Site page");
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocator.LABEL_SITE_LOCATOR.get())))
                .getText();
    }




}
