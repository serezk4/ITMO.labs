package com.serezka.lab;

import com.serezka.lab.core.io.socket.server.tcp.TCPServerWorker6;
import com.serezka.lab.core.io.socket.server.tcp.TCPServerWorker7;
import com.serezka.lab.core.runner.Runner;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
@Log4j2
public class Application implements ApplicationRunner {
    Runner runner;

    TCPServerWorker6 tcpServerWorker6;
    TCPServerWorker7 tcpServerWorker7;

    public Application(@Qualifier("lab5runner") Runner runner,
                       TCPServerWorker6 tcpServerWorker6, TCPServerWorker7 tcpServerWorker7) {
        this.runner = runner;

        this.tcpServerWorker6 = tcpServerWorker6;
        this.tcpServerWorker7 = tcpServerWorker7;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        if (args.getNonOptionArgs().contains("-console"))
            new Thread(runner).start();

        tcpServerWorker6.init();
        tcpServerWorker7.init();
    }
}
