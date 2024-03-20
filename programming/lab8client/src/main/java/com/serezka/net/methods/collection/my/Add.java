package com.serezka.net.methods.collection.my;

import com.serezka.net.Method;
import com.serezka.objects.Flat;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import okhttp3.Response;

import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Add extends Method<Flat> {
    Flat flat;

    public Add(Flat flat) {
        super("/collection/my/add");
        this.flat = flat;
    }

    @Override
    public String getUrl() {
        return getBaseUrl();
    }

    @Override
    protected Flat mapResponse(Response response) throws IOException {
        return null;
    }

    @Override
    public Flat execute() throws IOException {
        return null;
    }
}
