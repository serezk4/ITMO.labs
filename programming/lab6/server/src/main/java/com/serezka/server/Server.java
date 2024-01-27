package com.serezka.server;

import com.serezka.server.controller.handler.Handler;
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
	Handler handler;

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		new Thread(handler).start();
	}
}
