package com.serezka.net.methods.collection;

import com.google.gson.Gson;
import com.serezka.configuration.RuntimeConfiguration;
import com.serezka.net.Method;
import com.serezka.net.RestClient;
import com.serezka.objects.Flat;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import okhttp3.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class FindAll extends Method<List<Flat>> {
    Gson gson = new Gson();

    public FindAll() {
        super("/collection/all");

    }

    @Override
    public String getUrl() {
        return getBaseUrl();
    }

    @Override
    protected List<Flat> mapResponse(Response response) throws IOException {
        if (response == null) return Collections.emptyList();
        return gson.fromJson(response.body().string(), List.class);
    }

    @Override
    public List<Flat> execute() throws IOException {
        return mapResponse(RestClient.getInstance().get(this, RuntimeConfiguration.getJwtToken()));
    }
}
