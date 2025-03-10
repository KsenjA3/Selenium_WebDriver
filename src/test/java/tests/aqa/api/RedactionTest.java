package tests.aqa.api;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.aqa.api.po.OnlinerAboutPage;
import tests.aqa.api.requests.GetRequest;
import tests.aqa.ui.BaseTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class RedactionTest extends BaseTest {
    private static final String BASE_URL = "https://blog.onliner.by/about";
    OnlinerAboutPage onlinerAboutPage;

    @BeforeEach
    void openPage()  {
        driverSet.forEach(driver -> {
            driver.get(BASE_URL);
            onlinerAboutPage=new OnlinerAboutPage(driver);
            log.info("The site page " + driver.getCurrentUrl()+ " is opened");
            onlinerAboutPage.click_href_fast_connection_with_redaction();

            ArrayList <String> tabs = new ArrayList (driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            log.info(driver);
            log.info("The site page " + driver.getCurrentUrl()+ " is opened");

        });
    }


    @DisplayName("Verify redaction contacts")
    @Test
    public void verify_redactionContacts_Test() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
        Response response = GetRequest.makeGetRequestAndGetResponse(driver.getCurrentUrl(), headers, null);

        assertEquals(200, response.getStatusCode());
        assertTrue( response.getBody().asString().contains("Контакты редакции. Манифест. Спецпроекты. Правила использования материалов"));
        assertTrue( response.getBody().asString().contains("<p>Телефон: <strong>(+375 17) </strong><strong>270-13-30</strong><strong>.</strong></p>"));

    }

}
