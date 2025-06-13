package tests.aqa.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.files.DownloadActions.click;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@Log4j2
public class OnlinerTest extends BaseTest{

    //input[@name='query']
    //a[@class='product__description']
    //h1.catalog-masthead__title
    //span[@class="button-style button-style_another button-style_base product-aside__button"]
    private final String NAME_SEARCH_ITEM = "Телефон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)";


    @Test
    public void openHomePage() throws InterruptedException {
        open("/");

        $(By.name("query")).shouldBe(Condition.visible).setValue(NAME_SEARCH_ITEM);


        $(".modal-iframe").shouldBe(Condition.visible);
        switchTo().frame($(".modal-iframe"));
        $(By.xpath("//a[@class='product__description']")).shouldBe(Condition.clickable).click();
//        Thread.sleep(1000);

//        switchTo().defaultContent();
//        Selenide.actions().sendKeys(Keys.ESCAPE).perform();

//        SelenideElement popup =
//                $(By.xpath("//*[@class='popover-style popover-style_alter popover-style_small popover-style_bottom-right product-aside__popover product-aside__popover_delivery offers-list__popover_width_xxs popover-style_visible']")); // Ваш локатор для поп-апа
//        popup.shouldBe(Condition.clickable).pressEscape();

        String actual_item_name =$(By.cssSelector("h1.catalog-masthead__title")).shouldBe(Condition.visible).getText();
        assertEquals(NAME_SEARCH_ITEM,actual_item_name);
        log.info(actual_item_name);
//        $(By.cssSelector("h1.catalog-masthead__title")).shouldHave(Condition.text(NAME_SEARCH_ITEM), Duration.ofSeconds(6));
//        $(By.cssSelector("h1.catalog-masthead__title")).shouldBe(Condition.visible, Condition.text(NAME_SEARCH_ITEM));


// Получаем текущий URL
        String expectedUrl = "https://catalog.onliner.by/mobile/samsung/sma525fzkdser";
        String currentUrl = webdriver().driver().url();
        assertEquals(expectedUrl,currentUrl);
        log.info(currentUrl);
//        webdriver().shouldHave(url(expectedUrl));



        // Пример ассерта:
//        currentUrl.shouldBe(Condition.text(expectedUrl)); // Или более точный URL
    }



}
