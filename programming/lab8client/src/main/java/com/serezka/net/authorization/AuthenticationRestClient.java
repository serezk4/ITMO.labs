package com.serezka.net.authorization;

import com.serezka.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AuthenticationRestClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = Configuration.SERVER_URL;

    @Autowired
    public AuthenticationRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AuthenticationResponse register(String mail, String username, String password) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("mail", mail);
        map.add("username", username);
        map.add("password", password);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity(baseUrl + "/register", request, AuthenticationResponse.class);
        return response.getBody();
    }

    public AuthenticationResponse login(String username, String password) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity(baseUrl + "/login", request, AuthenticationResponse.class);
        return response.getBody();
    }
}

