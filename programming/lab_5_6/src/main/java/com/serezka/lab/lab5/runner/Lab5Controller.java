package com.serezka.lab.lab5.runner;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.runner.web.ChatRequest;
import com.serezka.lab.core.runner.Runner;
import com.serezka.lab.lab5.hahdler.Chat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/lab5")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ComponentScan("com.serezka.lab.core.command")
@Log4j2
public class Lab5Controller implements Runner {
    Chat chat;

    public Lab5Controller(@Qualifier("lab5handler") Chat chat) {
        this.chat = chat;
    }

    @GetMapping("/")
    public String chatPage() {
        return "lab5";
    }

    @PostMapping("/send")
    @ResponseBody
    public Message sendMessage(@RequestBody ChatRequest chatRequest) {
        try {
            chatRequest.getTemporaryCollection().forEach(flat -> flat.setUserId(Chat.USER_ID));
            Payload payload = new Payload(chatRequest.getMessage(), chatRequest.getTemporaryCollection(), "test");
            Response response = chat.handle(payload);
            return new Message(response.getMessage(), response.getFlats());
        } catch (Exception ex) {
            return new Message(ex.getMessage());
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
