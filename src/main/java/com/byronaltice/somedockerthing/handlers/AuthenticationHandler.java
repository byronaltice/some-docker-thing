package com.byronaltice.somedockerthing.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

@Component
@Slf4j
public class AuthenticationHandler {

    private final RestTemplate restTemplate = new RestTemplate();

    public String handleRequest(String data) {
        String endpoint = "http://google.com";
        log.info("Authenticate user {} with endpoint {}", data, endpoint);
        HttpEntity<String> entity = new HttpEntity<>("body", getHeaders(data, data));
        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(endpoint + "/" + data, HttpMethod.POST, entity, String.class);
        } catch (HttpServerErrorException e) {
            String message = "Error while authenticating against layer7 with appId: " + data;
            // TODO: logging e here doesn't do anything. It's just blank in the logs for some reason.
            log.error(message, e);
            throw new RuntimeException(message, e);
        }
        String body = response.getBody();
        return body;
    }

    private HttpHeaders getHeaders(String username, String password) {
        String basicAuthenticationToken = getBasicAuthenticationToken(username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + basicAuthenticationToken);
        return headers;
    }

    private String getBasicAuthenticationToken(String user, String password) {
        String tokenBasicAuthentication = user + ":" + password;
        return Base64.getEncoder().encodeToString(tokenBasicAuthentication.getBytes(StandardCharsets.UTF_8));
    }

}
