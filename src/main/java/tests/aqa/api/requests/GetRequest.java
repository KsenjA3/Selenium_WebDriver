package tests.aqa.api.requests;

import io.restassured.response.Response;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;

import static io.restassured.RestAssured.given;

public class GetRequest {

    public static Response makeGetRequestAndGetResponse(
            String endpoint, Map<String, Object> header, Map<String, Object> params) {
        return given().headers(MapUtils.emptyIfNull(header))
                .params(MapUtils.emptyIfNull(params))
                .when()
                .get(endpoint)
                .then()
                .log()
                .all(true)
                .extract()
                .response();
    }
}
