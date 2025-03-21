package tests.aqa.ui.po.aspect;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.utils.ConfProperties;

import java.time.Duration;

@Log4j2
public class LoginPageAspect {
    private WebDriver driver;

    public LoginPageAspect(WebDriver driver){
        this.driver = driver;
        driver.get(ConfProperties.getProperty("aspect_page"));
    }

    //Set user name in textbox
    public void setUserName(String strUserName){
        log.info("set user name to " + strUserName);
        driver.findElement(By.xpath(LoginPageAspectLocator.USERNAME_INPUT_LOCATOR.getLocator())).sendKeys(strUserName);
    }

    //Set password in password textbox
    public void setPassword(String strPassword){
        log.info("set password to " + strPassword);
        driver.findElement(By.xpath(LoginPageAspectLocator.PASSWORD_INPUT_LOCATOR.getLocator())).sendKeys(strPassword);
    }

    //Click on login button
    public void clickLogin(){
        log.info("click login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath(LoginPageAspectLocator.LOGIN_BUTTON_LOCATOR.getLocator())));
        element.click();
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

    public String tryLoginPage(String strUserName,String strPasword){
        loginPage(strUserName, strPasword);
        return driver.getCurrentUrl();
    }

    public String tryLoginPageReturnInfoMessageOfResult(String strUserName,String strPasword){
        loginPage(strUserName, strPasword);
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://aspect.t8s.ru/")){
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageAspectLocator.ERROR_REPORT_IDENTIFICATION_LOCATOR.getLocator())))
                    .getText();
        }
        return currentUrl;
    }

    public String getNameLabelCompany() {
        return   new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageAspectLocator.NAME_COMPANY_LABEL_SITE_LOCATOR.getLocator())))
                .getText();
    }

    public String getAttributeFieldLoginDataValRequired() {
        return   new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageAspectLocator.USERNAME_INPUT_LOCATOR.getLocator())))
                .getDomAttribute("data-val-required");
    }


    public boolean isDisplayedLabelCompany() {
        return   new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageAspectLocator.COMPANY_LABEL_SITE_LOCATOR.getLocator())))
                .isDisplayed();
    }

    public String getColorLoginButton() {
        String color = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPageAspectLocator.LOGIN_BUTTON_LOCATOR.getLocator())))
                .getCssValue("color");
        if (color.startsWith("rgba(")) {
            color = color.replace("rgba(", "rgb(");
            int index = color.lastIndexOf(",");
            color = color.substring(0, index)+")";
        }
        log.info("Color: " + color);
        log.info(driver);
        return color;
    }

    public String clickForgotPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath(LoginPageAspectLocator.HREF_FORGOT_PASSWORD_SITE_LOCATOR.getLocator())));
        element.click();
       return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
