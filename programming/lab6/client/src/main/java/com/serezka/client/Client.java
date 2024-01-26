package com.serezka.client;

import com.google.gson.Gson;
import com.serezka.client.channel.ChannelWorker;
import com.serezka.client.channel.TCPChannelWorker;
import com.serezka.client.object.Person;
import com.serezka.client.object.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Client implements ApplicationRunner {

	TCPChannelWorker tcpChannelWorker;

	public static void main(String[] args) {
		SpringApplication.run(Client.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		new Thread(tcpChannelWorker).start();

	}
}
