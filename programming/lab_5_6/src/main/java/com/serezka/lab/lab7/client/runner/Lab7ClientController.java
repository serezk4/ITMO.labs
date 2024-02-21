package com.serezka.lab.lab7.client.runner;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.model.User;
import com.serezka.lab.core.io.socket.client.tcp.TCPClientWorker6;
import com.serezka.lab.core.io.socket.client.tcp.TCPClientWorker7;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.io.socket.objects.State;
import com.serezka.lab.core.runner.Runner;
import com.serezka.lab.core.runner.web.ChatRequest;
import com.serezka.lab.lab5.hahdler.Chat;
import com.serezka.lab.lab7.client.handler.Lab7ClientHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/lab7/client")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class Lab7ClientController implements Runner {
    TCPClientWorker7 tcpClientWorker7;
    Lab7ClientHandler handler;

    public Lab7ClientController(TCPClientWorker7 tcpClientWorker7, @Qualifier("lab7client") Lab7ClientHandler handler) {
        this.tcpClientWorker7 = tcpClientWorker7;
        this.handler = handler;
    }

    @GetMapping("/getResponse")
    @ResponseBody
    public Message getResponse() {
        Response response = handler.getResponse();
        if (response == null) return null;

        return new Message(response.getMessage(), response.getFlats(), response.getState());
    }

    @GetMapping("/")
    public String chatPage() {
        return "lab7";
    }

    @GetMapping("/info")
    @ResponseBody
    public Message getInfo() {
        return new Message(tcpClientWorker7.getInfo());
    }

    @GetMapping("/isConnected")
    @ResponseBody
    public Message isActive() {
        return new Message(tcpClientWorker7.isConnected() ? "Active" : "Inactive");
    }

    @PostMapping("/connect")
    @ResponseBody
    public Message connect() {
        tcpClientWorker7.init();
        return new Message("connected");
    }

    @PostMapping("/disconnect")
    @ResponseBody
    public Message disconnect() {
        tcpClientWorker7.disconnect();
        return new Message("отключено");
    }

    @PostMapping("/send")
    @ResponseBody
    public Message sendMessage(@RequestBody ChatRequest chatRequest) {
        try {
            chatRequest.getTemporaryCollection().forEach(flat -> flat.setUserId(Chat.USER_ID));

            Payload payload = new Payload(chatRequest.getMessage(), chatRequest.getTemporaryCollection(), "test");
            payload.setUsername(chatRequest.getUsername());
            payload.setPassword(chatRequest.getPassword());

            Response response = handler.handle(payload);
            return new Message(response.getMessage(), response.getFlats());
        } catch (Exception ex) {
            return new Message(ex.getMessage());
        }
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    public static class Message {
        String message;
        Set<Flat> flats;
        String state;

        public Message(String message, Set<Flat> flats) {
            this(message);
            this.flats = flats;
            this.state = State.OK.name();
        }

        public Message(String message, Set<Flat> flats, State state) {
            this(message);
            this.flats = flats;
            this.state = Optional.ofNullable(state).orElse(State.OK).name();
        }

        public Message(String message) {
            this.message = message.replaceAll("\n", "</br>");
        }

        public void setMessage(String message) {
            this.message = message.replaceAll("\n", "</br>");
        }
    }
}