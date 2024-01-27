package com.serezka.server.controller.serializer;

import com.serezka.server.controller.handler.Payload;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;

public interface SerializerDeserializer<T> extends Serializer<T>, Deserializer<T> {

}
