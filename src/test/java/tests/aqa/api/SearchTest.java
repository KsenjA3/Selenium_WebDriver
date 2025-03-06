package tests.aqa.api;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import tests.aqa.api.requests.GetRequest;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {
    private static final String CATALOG_URL = "https://catalog.onliner.by/";
    private static final String AUTO_URL = "https://go.onliner.by/tiles.acp/redirect/eyJ1cmwiOiJodHRwczpcL1wvYXV0by5vbmxpbmVyLmJ5XC8yMDI1XC8wM1wvMDZcL2RvbGctemEtYXZ0b3hsYW0iLCJpbmRleCI6MiwiaWRlbnRpdHkiOiIxOjA6MDoxNzQxMjM4MTcwIn0%3D";
    private static final String BARACHOLKA_FIND_DOM_URL = "https://baraholka.onliner.by/search.php";

    @Description("Verify access catalog page")
    @Test
    public void catalogAccessTest() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
        Response response = GetRequest.makeGetRequestAndGetResponse(CATALOG_URL, headers, null);

        assertEquals(200, response.getStatusCode());
        assertTrue( response.getBody().asString().contains("<title>Каталог Onlíner</title>"));
    }


    @Description("Verify header Server on AutoNews Page")
    @Test
    public void headerServerAutoNewsPageTest() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
        Response response = GetRequest.makeGetRequestAndGetResponse(AUTO_URL, headers, null);

        assertEquals(200, response.getStatusCode());
        response.then().assertThat()
                .header("server", equalTo("nginx"));
    }

    @Description("Verify presence in section search of BARACHOLKA item DOM")
    @Test
    public void presenceDom_inSectionSearch_ofBaracholka_Test() {
        Map<String, Object> headers = new HashMap<>();
            headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
        Map<String, Object> params= new HashMap<>();
            params.put("q", "дом");
        Response response = GetRequest.makeGetRequestAndGetResponse(BARACHOLKA_FIND_DOM_URL, headers, null);
        String body = response.getBody().asString();

        assertEquals(200, response.getStatusCode());
        assertTrue(body.contains("Поиск на Барахолке"));
        assertTrue(body.contains("дом"));
    }

}
