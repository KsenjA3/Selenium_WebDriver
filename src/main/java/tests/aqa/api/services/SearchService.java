package tests.aqa.api.services;

import io.restassured.response.Response;
import tests.aqa.api.requests.GetRequest;
import java.util.HashMap;
import java.util.Map;

public class SearchService {

    public Response search (String url, Map<String, Object> params)  {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
        return GetRequest.makeGetRequestAndGetResponse(url, headers, params);
    }
}
