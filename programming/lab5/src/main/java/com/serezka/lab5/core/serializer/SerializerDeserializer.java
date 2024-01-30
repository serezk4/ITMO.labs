package com.serezka.lab5.core.serializer;

import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;

public interface SerializerDeserializer<T> extends Serializer<T>, Deserializer<T> {

}
