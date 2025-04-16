package tests.aqa.api.onliner;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.aqa.api.onliner.po.OnlinerAboutPage;
import tests.aqa.api.onliner.requests.GetRequest;
import tests.aqa.ui.BaseTest;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@Log4j2
public class RedactionTest extends BaseTest {
    private static final String BASE_URL = "https://blog.onliner.by/about";
    Set<OnlinerAboutPage> onlinerAboutPages;

    @BeforeEach
    void openPage()  {
        onlinerAboutPages = new HashSet<>();
        driverSet.forEach(driver -> {
            driver.get(BASE_URL);

            OnlinerAboutPage onlinerAboutPage = new OnlinerAboutPage(driver);
            onlinerAboutPages.add(onlinerAboutPage);
            log.info("The site page " + driver.getCurrentUrl()+ " is opened");
            onlinerAboutPage.clickHrefFastConnectionWithRedaction();

            ArrayList <String> tabs = new ArrayList (driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.titleIs("Контакты редакции. Манифест. Спецпроекты. Правила использования материалов - Люди Onlíner") );
            log.info("The site page " + driver.getCurrentUrl()+ " is opened");
        });
    }

    @DisplayName("Verify redaction contacts")
    @Test
    public void verifyRedactionContactsTest() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
        onlinerAboutPages.forEach(onlinerAboutPage -> {
            Response response = GetRequest.makeGetRequestAndGetResponse(onlinerAboutPage.getURL(), headers, null);
            log.info(onlinerAboutPage.getDriver());
            assertEquals(200, response.getStatusCode());
            assertTrue( response.getBody().asString().contains("Контакты редакции. Манифест. Спецпроекты. Правила использования материалов"));
            assertTrue( response.getBody().asString().contains("<p>Телефон: <strong>(+375 17) </strong><strong>270-13-30</strong><strong>.</strong></p>"));
        });
    }
}
