package com.serezka.lab;

import com.serezka.lab.core.io.socket.client.tcp.TCPClientWorker;
import com.serezka.lab.core.io.console.ConsoleWorker;
import com.serezka.lab.core.io.socket.server.tcp.TCPServerWorker;
import com.serezka.lab.lab5.hahdler.Chat;
import com.serezka.lab.lab6.client.handler.Client;
import com.serezka.lab.lab6.server.handler.Server;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
@Log4j2
public class Application implements ApplicationRunner {
    Chat chat;

    Server server;
    Client client;

    @Qualifier("TCPServerWorker")
    TCPServerWorker tcpServerWorker;
    TCPClientWorker tcpClientWorker;

    ConsoleWorker consoleWorker;

    public Application(Chat chat, Server server, Client client, @Qualifier("TCPServerWorker") TCPServerWorker tcpServerWorker, TCPClientWorker tcpClientWorker, ConsoleWorker consoleWorker) {
        this.chat = chat;
        this.server = server;
        this.client = client;
        this.tcpServerWorker = tcpServerWorker;
        this.tcpClientWorker = tcpClientWorker;
        this.consoleWorker = consoleWorker;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Use gradle 8.5 & Java 21");

        final String mode = consoleWorker.get("select lab [5/6]: ");

        if (mode.equals("5")) {
            new Thread(chat).start();
            return;
        }

        if (mode.equals("6")) {
            tcpServerWorker.init();
            tcpClientWorker.init();
//            new Thread(tcpServerWorker).start();;
//            new Thread(tcpClientWorker).start();
//            new Thread(server).start();
            return;
        }

        log.warn("can't start selected mode!");
    }
}
