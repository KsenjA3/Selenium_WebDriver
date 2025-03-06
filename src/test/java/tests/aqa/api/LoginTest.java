package tests.aqa.api;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.aqa.BaseTest;
import lombok.extern.log4j.Log4j2;
import tests.aqa.api.requests.PostRequest;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class LoginTest extends BaseTest {
    private static final String BASE_URL = "https://catalog.onliner.by/sdapi/user.api/login";

    @DisplayName("Check login with empty data")
//    @Test
    public void testPostEmptyEmailAndPassword() {
        String body = """
                {
                    "login" : "", 
                    "password" : ""
                }
                """;
//        log.info("Request body: " + body);
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String expectedError1 = "Validation failed";
        String expectedError2 = "Укажите ник или e-mail";
        String expectedError3 = "Укажите пароль";

        Response response =
                PostRequest.makePostRequestAndGetResponse( BASE_URL, headers, body);

        assertEquals(response.getStatusCode(), 422);
        String responseString = response.getBody().asString();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        response.then().assertThat().body("message", Matchers.equalTo(expectedError1));




//        given().header("Content-Type", "application/json").body(body)
//                .when().post(BASE_URL)
//                .then().assertThat().statusCode(422)
//                .body("message", Matchers.equalTo(expectedError1))
//                .body("errors.login",containsInAnyOrder(expectedError2))
//                .body("errors.password",containsInAnyOrder(expectedError3))
//        ;
    }


    @DisplayName("Check login by unregistered user")
//    @Test
    public void testPostNonExistingCredentials() {
        String body = """
                {
                    "login" : "111111",
                    "password" : "111111"
                }
                """;
        log.info("Request body: " + body);
        String expectedError1 = "invalid_login_or_password";
        String expectedError2 = "Неверный логин или пароль";
        System.out.println(
        given().header("Content-Type", "application/json").body(body)
                .when().post(BASE_URL)
                .then().assertThat().statusCode(400)
                .extract()
                .response().getBody().asString()
        )

//                .body("errors[0].key",Matchers.equalTo(expectedError1))
        ;
    }


}
