package tests.aqa.po;

import lombok.extern.log4j.Log4j2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.ConfProperties;

import java.time.Duration;

@Log4j2
public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
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

    // Return to login page from home page, it was successful registration
    public String loginPage_and_saveURLdriver(String strUserName,String strPasword){
        loginPage(strUserName, strPasword);

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://aspect.t8s.ru/")){
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocator.ERROR_REPORT_IDENTIFICATION_LOCATOR.get())))
                    .getText();
        }
        return currentUrl;
    }


    public String getNameLabelCompany() {
        return   new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocator.NAME_COMPANY_LABEL_SITE_LOCATOR.get())))
                .getText();
    }

    public String getAttribute_FieldLogin_data_val_required() {
        return   new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocator.USERNAME_INPUT_LOCATOR.get())))
                .getDomAttribute("data-val-required");
    }


    public boolean isDisplayed_LabelCompany() {
        return   new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocator.COMPANY_LABEL_SITE_LOCATOR.get())))
                .isDisplayed();
    }

    public String getColor_LoginButton() {
        return   new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageLocator.LOGIN_BUTTON_LOCATOR.get())))
                .getCssValue("color");
    }

    public void click_forgotPassword() {
         driver.findElement(By.xpath(LoginPageLocator.HREF_FORGOT_PASSWORD_SITE_LOCATOR.get())).click();
    }


}
