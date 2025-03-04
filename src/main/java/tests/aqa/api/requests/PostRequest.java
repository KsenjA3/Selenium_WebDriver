package tests.aqa.api.requests;

import static io.restassured.RestAssured.given;

public class PostRequest {
    public static Response makePostRequestAndGetResponse(
            String endpoint, Map<String, Object> header, Object body) {
        return given().log()
                .all(true)
                .headers(MapUtils.emptyIfNull(header))
                //                .params(MapUtils.emptyIfNull(params))
                .body(body != null ? body : "{}")
                .when()
                .post(endpoint)
                .then()
                .log()
                .all(true)
                .extract()
                .response();
    }
}
