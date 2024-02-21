package com.serezka.lab.core.command;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.io.socket.objects.Payload;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Bridge {
    // const
    @Getter final Long userId;

    // input data
    @Getter final String inputCommand;
    @Getter @Setter String inputText;
    @Getter @Setter Set<Flat> inputData;

    /*internal stack*/  @Getter final Stack<Payload> internalQueries = new Stack<>();
    /*products*/        @Getter final Set<Flat> nestedProducts = new HashSet<>();

    // constructor
    public Bridge(Long userId, String inputCommand, String inputText, Set<Flat> inputData) {
        this.userId = userId;
        this.inputCommand = inputCommand;
        this.inputText = inputText;
        this.inputData = inputData;
    }

    public void addInternalQuery(Payload internalQuery) {
        this.internalQueries.add(internalQuery);
    }
    public void addInternalQueries(List<Payload> internalQueries) {
        this.internalQueries.addAll(internalQueries);
    }

    public void addNestedFlat(Flat nestedProduct) {
        this.nestedProducts.add(nestedProduct);
    }
    public void addNestedFlats(Set<Flat> nestedProducts) {
        this.nestedProducts.addAll(nestedProducts);
    }

    // text
    final StringBuilder builder = new StringBuilder();

    public void send(String text) {
        this.builder.append(text).append("\n");
    }

    public void send(String pattern, Object... objects) {
        this.send(String.format(pattern, objects));
    }

    public String getText() {
        return this.builder.toString();
    }
}
