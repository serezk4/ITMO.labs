package com.serezka.lab6server.handler.handler;

import com.serezka.lab6server.handler.command.Command;
import com.serezka.lab6server.handler.io.channel.ChannelWorker;
import com.serezka.lab6server.handler.io.format.FormatWorker;
import com.serezka.lab6server.handler.transaction.TransactionManager;
import com.serezka.lab6server.handler.user.Data;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Handler {
    @Setter @Getter @NonFinal
    private List<Command> commands = new ArrayList<>();

    @NonFinal @Setter
    Data data;

    public Data getData() {
        if (!TransactionManager.isEmpty()) return TransactionManager.get().getData();
        return this.data;
    }

    FormatWorker formatWorker;

    @Getter
    ChannelWorker channel;

    public Handler(FormatWorker formatWorker,
                   ChannelWorker channel) {
        this.formatWorker = formatWorker;
        this.channel = channel;
    }

    public String handle(Message<?> message) {
        Update update = (Update) message.getPayload();

        return "hello!";
    }
}
