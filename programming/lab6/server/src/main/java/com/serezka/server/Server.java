package com.serezka.server;

import com.serezka.server.controller.io.channel.tcp.TCPChannelWorker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Server implements ApplicationRunner {
	TCPChannelWorker tcpChannelWorker;

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		new Thread(tcpChannelWorker).start();
	}
}
