package tests.aqa.ui.po.saucedemo;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePageSaucedemo {
    private WebDriver driver;

    public HomePageSaucedemo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@data-test=\"product-sort-container\"]")
    private WebElement selector;

    @FindBy(xpath = "//option[@value=\"lohi\"]")
    private WebElement optionSortByPriceFromLowToHigh;

    @Getter
    @FindBy(className = "inventory_item_price")
    private List<WebElement> pricesWebElement;

    public void sortByPriceFromLowToHigh(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(this.selector)).click();
        wait.until(ExpectedConditions.elementToBeClickable(this.optionSortByPriceFromLowToHigh)).click();
    }

    public List<Double> getPrices() {
       return pricesWebElement.stream()
                .map(element -> element.getText().replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
