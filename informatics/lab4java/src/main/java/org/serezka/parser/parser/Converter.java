package org.serezka.parser.parser;

import org.serezka.parser.formats.Format;

public interface Converter<K, E extends Format> {
    K convert(E e);
}
