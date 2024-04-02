package com.serezka.net.collection;

import com.serezka.configuration.Configuration;
import com.serezka.configuration.RuntimeConfiguration;
import com.serezka.objects.Flat;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import okhttp3.internal.http2.Header;
import org.apache.catalina.connector.Request;
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
    String baseUrl = Configuration.SERVER_URL + "/collection"; // URL вашего API
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
                tokenHeader,Flat[].class);
        if (response.getBody() == null) return Collections.emptyList();
        return Arrays.asList(response.getBody());
    }

    public Integer findAllSize() {
        ResponseEntity<Integer> response = restTemplate.getForEntity(baseUrl + "/size", Integer.class);
        return response.getBody();
    }

    public Flat addFlat(Flat flat) {
        HttpEntity<Flat> request = new HttpEntity<>(flat, getDefaultHeaders());

        ResponseEntity<Flat> response = restTemplate.postForEntity(baseUrl + "/my/add", request, Flat.class);
        return response.getBody();
    }
}
