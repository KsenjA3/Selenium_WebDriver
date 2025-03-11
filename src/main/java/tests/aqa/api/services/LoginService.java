package tests.aqa.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import tests.aqa.api.models.LoginBody;
import tests.aqa.api.requests.PostRequest;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class LoginService {
    private static final String BASE_URL = "https://catalog.onliner.by/sdapi/user.api/login";

    public Response verifyLogin(String login, String password)  {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        String jsonBody;

        /**
         * I способ
         */
//        LoginBody body = LoginBody.builder()
//                .login(login)
//                .password(password)
//                .build();
//        try { jsonBody = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(body);}
//        catch (JsonProcessingException e) { log.error(e); }

        /**
         * II способ
         * не требует дополнительных классов models.LoginBody
         */
        JSONObject jObject = new JSONObject();
        jObject.put("login", login);
        jObject.put("password", password);
        jsonBody = jObject.toString();

        return PostRequest.makePostRequestAndGetResponse( BASE_URL, headers, jsonBody);
    }
}
