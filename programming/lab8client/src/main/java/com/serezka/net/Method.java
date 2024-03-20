package com.serezka.net;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@RequiredArgsConstructor
public abstract class Method<K> {
    String baseUrl;

    public abstract String getUrl();

    public RequestBody getBody() {
        return RequestBody.create(null, "");
    }

    protected abstract K mapResponse(Response response) throws IOException;

    public abstract K execute() throws IOException;
}
