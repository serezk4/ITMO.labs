package org.serezka.parser.formats;

import java.io.Serializable;
import java.util.List;

public class XML extends Format implements Serializable {
    @Override
    public int add(List<String> data) {
        return 0;
    }

    @Override
    public int add(String data) {
        return 0;
    }

    @Override
    public int insert(int id, List<String> data) {
        return 0;
    }

    @Override
    public int insert(int id, String data) {
        return 0;
    }
}
