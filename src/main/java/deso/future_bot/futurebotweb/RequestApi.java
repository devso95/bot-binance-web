package deso.future_bot.futurebotweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
public class RequestApi {

    final String BASE_URL = "http://localhost:8222/api";

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> call(String path, HttpMethod method, Object body) {
        HttpHeaders headers =
                new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(BASE_URL + path, method, entity, String.class);
    }

    public ResponseEntity<String> call(String path, HttpMethod method, Object body, String token) {
        HttpHeaders headers =
                new HttpHeaders();
        headers.set("authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(BASE_URL + path, method, entity, String.class);
    }

    public ResponseEntity<String> get(String path, String token) {
        HttpHeaders headers =
                new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("authorization", "Bearer " + token);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(BASE_URL + path, HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity<String> callWithNoBody(String path, HttpMethod method, String token) {
        HttpHeaders headers =
                new HttpHeaders();
        headers.set("authorization", "Bearer " + token);
        HttpEntity<Object> entity = new HttpEntity<>(token, headers);
        return restTemplate.exchange(BASE_URL + path, method, entity, String.class);
    }
}
