package com.serezka.lab;

import com.serezka.lab.core.io.socket.client.tcp.TCPClientWorker;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.State;
import com.serezka.lab.core.io.socket.server.tcp.TCPServerWorker;
import com.serezka.lab.core.runner.Runner;
import com.serezka.lab.lab5.hahdler.Chat;
import com.serezka.lab.lab6.client.handler.Client;
import com.serezka.lab.lab6.server.handler.Server;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@AutoConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
@Log4j2
public class Application implements ApplicationRunner {
    Chat chat;
    Runner runner;

    TCPServerWorker tcpServerWorker;
    TCPClientWorker tcpClientWorker;

    Server server;
    Client client;

    public Application(@Qualifier("lab5") Chat chat, @Qualifier("lab5runner") Runner runner, TCPServerWorker tcpServerWorker, TCPClientWorker tcpClientWorker, Server server, Client client) {
        this.chat = chat;
        this.runner = runner;
        this.tcpServerWorker = tcpServerWorker;
        this.tcpClientWorker = tcpClientWorker;
        this.server = server;
        this.client = client;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        if (args.getNonOptionArgs().contains("-console"))
            new Thread(runner).start();

        tcpServerWorker.init();
    }
}
