package tests.aqa.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tests.aqa.api.services.SearchService;
import tests.aqa.api.services.SearchURL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SearchTest {
    private static SearchService service;

    private static List<Arguments> provideArguments = Arrays.asList(
            arguments("Verify access catalog page", SearchURL.CATALOG_URL.getUrlSearch(), 200, "<title>Каталог Onlíner</title>"),
            arguments("Verify correct UNP on page about company", SearchURL.ABOUT_COMPANY_URL.getUrlSearch(), 200, "УНП 190657494")
    );

    @BeforeAll
    static void setUp() {
        service=new SearchService();
    }

    @ParameterizedTest (name = "{0}")
    @FieldSource("provideArguments")
    public void searchTest(String searchText, String url, int expectedStatusCode, String expectedBody) {
        Response response = service.search(url, null);

        assertEquals(expectedStatusCode, response.getStatusCode());
        assertTrue( response.getBody().asString().contains(expectedBody));
    }

    @DisplayName("Verify header Server on AutoNews Page")
    @Test
    public void headerServerAutoNewsPageTest() {
        Response response = service.search(SearchURL.AUTO_URL.getUrlSearch(), null);

        assertEquals(200, response.getStatusCode());
        response.then().assertThat().header("server", equalTo("nginx"));
    }

    @DisplayName("Verify presence in section search of BARACHOLKA item DOM")
    @Test
    public void presenceDom_inSectionSearch_ofBaracholka_Test() {
        Map<String, Object> params= new HashMap<>();
        params.put("q", "дом");
        Response response = service.search(SearchURL.BARACHOLKA_FIND_DOM_URL.getUrlSearch(), params);
        String body = response.getBody().asString();

        assertEquals(200, response.getStatusCode());
        assertTrue(body.contains("<h1 class=\"m-title-i \">Поиск на Барахолке</h1>"));
        assertTrue(body.contains("<input type=\"text\" class=\"i-p\" autocomplete=\"off\" placeholder=\"Поиск в разделе\" name=\"q\" value=\"дом\">"));
    }
}
