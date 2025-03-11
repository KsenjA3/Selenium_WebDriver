package tests.aqa.api.po;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class OnlinerAboutPage {
    private WebDriver driver;

    public OnlinerAboutPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickHrefFastConnectionWithRedaction(){
        log.info("click FAST_CONNECTION_WITH_REDACTION");
        WebElement element = driver.findElement(By.xpath(OnlinerAboutPageLocator.FAST_CONNECTION_WITH_REDACTION_LOCATOR.getLocator()));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
    public String getURL(){
        return driver.getCurrentUrl();
    }
    public WebDriver getDriver(){
        return driver;
    }

}
