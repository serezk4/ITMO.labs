package com.serezka.server.handler.handler;

import com.serezka.server.handler.command.Command;
import com.serezka.server.handler.io.channel.ChannelWorker;
import com.serezka.server.handler.io.format.FormatWorker;
import com.serezka.server.handler.object.Product;
import com.serezka.server.handler.transaction.TransactionManager;
import com.serezka.server.handler.user.Data;
import com.serezka.server.server.JsonSerializerDeserializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.messaging.Message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Handler {
    @Setter
    @Getter
    @NonFinal
    private List<Command> commands = new ArrayList<>();

    @NonFinal
    @Setter
    Data data;

    public Data getData() {
        if (!TransactionManager.isEmpty()) return TransactionManager.get().getData();
        return this.data;
    }

    FormatWorker formatWorker;

    @Getter
    ChannelWorker channel;

    JsonSerializerDeserializer updateSerializerAdapter;

    public Handler(FormatWorker formatWorker,
                   ChannelWorker channel, JsonSerializerDeserializer updateSerializerAdapter) {
        this.formatWorker = formatWorker;
        this.channel = channel;
        this.updateSerializerAdapter = updateSerializerAdapter;
    }

    public String handle(Message<?> message) {
        try {
            System.out.println("123");
        } catch (Exception ex) {
        }
        return "123";
    }
}

//    private byte[] serializeResponse(String response) throws IOException {
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//            updateSerializerAdapter.serialize(new Update(null, response), outputStream);
//            return outputStream.toByteArray();
//        }
//    }

