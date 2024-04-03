package com.serezka.net.collection;

import com.serezka.configuration.ServerConfiguration;
import com.serezka.configuration.RuntimeConfiguration;
import com.serezka.objects.Flat;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CollectionRestClient {
    String baseUrl = ServerConfiguration.SERVER_URL + "/collection"; // URL вашего API
    RestTemplate restTemplate;

    @Autowired
    public CollectionRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + RuntimeConfiguration.getJwtToken());
        return headers;
    }

    public List<Flat> findAll() {
        HttpEntity<String> tokenHeader = new HttpEntity<>(getDefaultHeaders());

        ResponseEntity<Flat[]> response = restTemplate.exchange(baseUrl + "/all",
                HttpMethod.GET,
                tokenHeader,
                Flat[].class);

        if (response.getBody() == null) return Collections.emptyList();
        return Arrays.asList(response.getBody());
    }

    public String remove(Flat element) {
        HttpEntity<String> tokenHeader = new HttpEntity<>(getDefaultHeaders());

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/remove" + "?id=" + element.getId(),
                HttpMethod.POST,
                tokenHeader,
                String.class);

        return response.getBody();
    }

    public String clear() {
        HttpEntity<String> tokenHeader = new HttpEntity<>(getDefaultHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/clear", tokenHeader, String.class);
        return response.getBody();
    }

    public Flat addFlat(Flat flat) {
        HttpEntity<Flat> request = new HttpEntity<>(flat, getDefaultHeaders());
        ResponseEntity<Flat> response = restTemplate.postForEntity(baseUrl + "/add", request, Flat.class);
        return response.getBody();
    }
}
