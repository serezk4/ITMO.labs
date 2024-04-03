package com.serezka.net.execution;

import com.serezka.configuration.ServerConfiguration;
import com.serezka.configuration.RuntimeConfiguration;
import com.serezka.objects.Flat;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExecutionRestClient {
    String baseUrl = ServerConfiguration.SERVER_URL; // Replace with your actual base URL
    RestTemplate restTemplate;

    @Autowired
    public ExecutionRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + RuntimeConfiguration.getJwtToken());
        return headers;
    }

    public Response executeCommand(String command, List<Flat> flats, String text) {
        Request request = new Request();
        request.setCommand(command);
        request.setFlats(flats);
        request.setText(text);

        HttpEntity<Request> entity = new HttpEntity<>(request, getDefaultHeaders());
        return restTemplate.postForObject(baseUrl + "/execute", entity, Response.class);
    }

}
