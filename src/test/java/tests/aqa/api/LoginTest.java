package tests.aqa.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.aqa.api.models.LoginBody;
import tests.aqa.api.services.LoginService;
import tests.aqa.ui.BaseTest;
import tests.aqa.api.requests.PostRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {
    private static final String BASE_URL = "https://catalog.onliner.by/sdapi/user.api/login";

    private static Stream<Arguments> provideStringsForBodyAndExpectedResults() {
        return Stream.of(
                Arguments.of(" ", " ", "contains", 422, "errors.login", "Укажите ник или e-mail", "errors.password", "Укажите пароль"),
                Arguments.of("111111", " ", "contains&equalTo", 422, "message", "Validation failed", "errors.password", "Укажите пароль" ),
                Arguments.of(" ", "11111", "contains&equalTo", 422, "message", "Validation failed", "errors.login", "Укажите ник или e-mail"),
                Arguments.of("111111", "111111", "equalTo", 400, "errors[0].key", "invalid_login_or_password", "errors[0].message", "Неверный логин или пароль")
        );
    }

    @ParameterizedTest (name = "Verify login = \"{0}\", password = \"{1}\", statusCode = {3}")
    @MethodSource("provideStringsForBodyAndExpectedResults")
    public void testVerifyDifferentOptionsOfEmailAndPassword(String login, String password, String method,
                                                             int expectedStatusCode,
                                                             String nameField1, String expectedResult1,
                                                             String nameField2, String expectedResult2)  {
        var response = new LoginService().verifyLogin(login, password);

        assertEquals(response.getStatusCode(), expectedStatusCode);
        switch (method) {
            case "contains"-> response.then().assertThat()
                    .body(nameField1, contains(expectedResult1))
                    .body(nameField2, contains(expectedResult2));
            case "equalTo"-> response.then().assertThat()
                    .body(nameField1, equalTo(expectedResult1))
                    .body(nameField2, equalTo(expectedResult2));
            case "contains&equalTo"-> response.then().assertThat()
                    .body(nameField1, equalTo(expectedResult1))
                    .body(nameField2, contains(expectedResult2));
        }
    }

}
