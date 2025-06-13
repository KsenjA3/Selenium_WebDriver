package tests.aqa.ui.po.aspect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.utils.ConfProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePageAspect {
    private WebDriver driver;

    public HomePageAspect(WebDriver driver){
        this.driver = driver;
        driver.get(ConfProperties.getProperty("aspect_page"));
    }

    public ArrayList<String> listOfItemsCourseLeftMenu() {
        ArrayList<String> arr = new ArrayList<>();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(HomePageAspectLocator.COURSE_LEFT_MENU_LOCATOR.getLocator())))
                .click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> arrWebElements =wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(HomePageAspectLocator.ITEMS_COURSE_LEFT_MENU_LOCATOR.getLocator())));

       for (WebElement elm : arrWebElements) {arr.add(elm.getText());}
        return arr;
    }
}
