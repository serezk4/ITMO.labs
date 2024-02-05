package com.serezka.lab.lab5.web;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.lab5.hahdler.Chat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/lab5")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ComponentScan("com.serezka.lab.core.command")
@Log4j2
public class ChatController {
    Chat chat;

    public ChatController(@Qualifier("lab5") Chat chat) {
        this.chat = chat;
    }

    @GetMapping("/")
    public String chatPage() {
        return "lab5";
    }

    @PostMapping("/send")
    @ResponseBody
    public Message sendMessage(@RequestParam("message") String message, @RequestParam("flats") Flat[] flats) {
        try {
            return new Message(chat.handle(new Payload(message, Arrays.stream(flats).collect(Collectors.toSet()), "test")).getMessage());
        } catch (Exception ex) {
            return new Message(ex.getMessage());
        }
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    static class Message {
        private String message;

        public Message(String message) {
            this.message = message.replaceAll("\n", "</br>");
        }

        public void setMessage(String message) {
            this.message = message.replaceAll("\n", "</br>");
        }
    }
}
