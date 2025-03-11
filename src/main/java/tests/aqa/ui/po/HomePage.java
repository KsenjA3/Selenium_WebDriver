package tests.aqa.ui.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.ConfProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get(ConfProperties.getProperty("log_page"));
    }

    public ArrayList<String> listOfItemsCourseLeftMenu() {
        ArrayList<String> arr = new ArrayList<>();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HomePageLocator.COURSE_LEFT_MENU_LOCATOR.getLocator())))
                .click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> arrWebElements =wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(HomePageLocator.ITEMS_COURSE_LEFT_MENU_LOCATOR.getLocator())));

       for (WebElement elm : arrWebElements) {arr.add(elm.getText());}
        return arr;
    }
}
