package com.serezka.lab.core.io.serializer;

import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;

public interface SerializerDeserializer<T> extends Serializer<T>, Deserializer<T> {

}
