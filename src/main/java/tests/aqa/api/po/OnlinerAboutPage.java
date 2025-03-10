package tests.aqa.api.po;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.ui.po.LoginPageLocator;
import java.time.Duration;

@Log4j2
public class OnlinerAboutPage {
    private WebDriver driver;

    public OnlinerAboutPage(WebDriver driver){
        this.driver = driver;
    }

    public void click_href_fast_connection_with_redaction(){
        log.info("click FAST_CONNECTION_WITH_REDACTION");
        WebElement element = driver.findElement(By.xpath(OnlinerAboutPageLocator.FAST_CONNECTION_WITH_REDACTION_LOCATOR.get()));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable
//                (By.xpath(OnlinerAboutPageLocator.FAST_CONNECTION_WITH_REDACTION_LOCATOR.get())));
//        element.click();
    }
}
