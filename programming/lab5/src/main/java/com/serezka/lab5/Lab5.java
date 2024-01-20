package com.serezka.lab5;

import com.serezka.lab5.chat.Chat;
import com.serezka.lab5.chat.command.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class Lab5 implements ApplicationRunner {
	Chat chat;

	public static void main(String[] args) {
		SpringApplication.run(Lab5.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// transactions
		chat.addCommand(new BeginTransaction());
		chat.addCommand(new CloseTransaction());
		chat.addCommand(new RollbackTransaction());

		// other commands
		chat.addCommand(new Add());
		chat.addCommand(new AddIfMax());
		chat.addCommand(new Clear());
		chat.addCommand(new ExecuteScript());
		chat.addCommand(new Exit());
		chat.addCommand(new FilterStartsWithPartNumber());
		chat.addCommand(new MinByCoordinates());
		chat.addCommand(new PrintAscending());
		chat.addCommand(new RemoveById());
		chat.addCommand(new RemoveGreater());
		chat.addCommand(new Reorder());
		chat.addCommand(new Save());
		chat.addCommand(new UpdateById());

		new Thread(chat).start();
	}
}
