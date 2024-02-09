package com.serezka.lab.lab6.client.runner;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.io.socket.client.tcp.TCPClientWorker;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.runner.Runner;
import com.serezka.lab.lab5.hahdler.Chat;
import com.serezka.lab.core.runner.web.ChatRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/lab6/client")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class Lab6ClientController implements Runner {
    TCPClientWorker tcpClientWorker;
    Handler<Response, Payload> handler;

    public Lab6ClientController(TCPClientWorker tcpClientWorker, @Qualifier("lab6client") Handler<Response, Payload> handler) {
        this.tcpClientWorker = tcpClientWorker;
        this.handler = handler;
    }

    @GetMapping("/")
    public String chatPage() {
        return "lab6";
    }

    @GetMapping("/info")
    public String getInfo() {
        return tcpClientWorker.getInfo();
    }

    @GetMapping("/isConnected")
    public boolean isActive() {
        return tcpClientWorker.isConnected();
    }

    @PostMapping("/connect")
    public void connect() {
        tcpClientWorker.init();
    }

    @PostMapping("/disconnect")
    public void disconnect() {
        tcpClientWorker.disconnect();
    }

    @PostMapping("/send")
    @ResponseBody
    public Message sendMessage(@RequestBody ChatRequest chatRequest) {
        try {
            chatRequest.getTemporaryCollection().forEach(flat -> flat.setUserId(Chat.USER_ID));
            Payload payload = new Payload(chatRequest.getMessage(), chatRequest.getTemporaryCollection(), "test");
            Response response = handler.handle(payload);
            return new Message(response.getMessage(), response.getFlats());
        } catch (Exception ex) {
            return new Message(ex.getMessage());
        }
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    static class Query {
        String scope;

        public Query(String scope) {
            this.scope = scope;
        }
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    static class Message {
        String message;
        Set<Flat> flats;

        public Message(String message, Set<Flat> flats) {
            this(message);
            this.flats = flats;
        }

        public Message(String message) {
            this.message = message.replaceAll("\n", "</br>");
        }

        public void setMessage(String message) {
            this.message = message.replaceAll("\n", "</br>");
        }
    }
}