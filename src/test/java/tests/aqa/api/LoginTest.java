package tests.aqa.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.aqa.ui.BaseTest;
import tests.aqa.api.requests.PostRequest;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {
    private static final String BASE_URL = "https://catalog.onliner.by/sdapi/user.api/login";

    @DisplayName("Verify login with empty data")
    @Test
    public void testPostEmptyEmailAndPassword() {

        /**
        * Initial data for request
        * BASE_URL - url testing web-site
        * body - body of request
        * headers - HashMap <String, Object>, where
         *            String - type of header name
         *           Object - value of header
         */
        String body = """
                {
                    "login" : "", 
                    "password" : ""
                }
                """;
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        /**
        *Expected results
         */
        String expectedError1 = "Validation failed";
        String expectedError2 = "Укажите ник или e-mail";
        String expectedError3 = "Укажите пароль";

        Response response = PostRequest.makePostRequestAndGetResponse( BASE_URL, headers, body);
        assertEquals(response.getStatusCode(), 422);
        response.then().assertThat()
                .body("message", equalTo(expectedError1))
                .body("errors.login",contains(expectedError2))
                .body("errors.password",contains(expectedError3));
    }

    @DisplayName("Verify login with empty password")
    @Test
    public void testPostAnyEmailEmptyPassword() {
        String body = """
                {
                    "login" : "111111",
                    "password" : ""
                }
                """;
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String expectedError1 = "Validation failed";
        String expectedError2 = "Укажите пароль";

        Response response = PostRequest.makePostRequestAndGetResponse( BASE_URL, headers, body);
        response.then().assertThat()
                .statusCode(422)
                .body("message", equalTo(expectedError1))
                .body("errors.password", contains(expectedError2))

        ;
    }

    @DisplayName("Verify login with empty email")
    @Test
    public void testPostEmptyEmailAnyPassword() {
        String body = """
                {
                    "login" : "",
                    "password" : "11111"
                }
                """;
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String expectedError1 = "Validation failed";
        String expectedError2 = "Укажите ник или e-mail";

        Response response = PostRequest.makePostRequestAndGetResponse( BASE_URL, headers, body);
        response.then().assertThat()
                .statusCode(422)
                .body("message", equalTo(expectedError1))
                .body("errors.login", contains(expectedError2))

        ;
    }

    @DisplayName("Verify login by unregistered user")
    @Test
    public void testPostNonExistingCustomer() {
        String body = """
                {
                    "login" : "111111",
                    "password" : "111111"
                }
                """;
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String expectedError1 = "invalid_login_or_password";
        String expectedError2 = "Неверный логин или пароль";

        Response response = PostRequest.makePostRequestAndGetResponse( BASE_URL, headers, body);
        response.then().assertThat()
                .statusCode(400)
                .body("errors[0].key", equalTo(expectedError1))
                .body("errors[0].message", equalTo(expectedError2))
        ;
    }

}
