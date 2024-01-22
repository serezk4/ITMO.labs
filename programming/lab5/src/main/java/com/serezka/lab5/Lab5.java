package com.serezka.lab5;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.command.*;
import com.serezka.lab5.chat.file_worker.CsvFileWorker;
import com.serezka.lab5.chat.obj.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@ToString
public class Lab5 implements ApplicationRunner {
    Chat chat;

    CsvFileWorker csvFileWorker;

    public static void main(String[] args) {
        SpringApplication.run(Lab5.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        csvFileWorker.write(List.of(Product.builder()
                        .id(200)
                .name("123")
                .coordinates(Coordinates.builder()
                        .x(1.3f)
                        .y(14L).build())
                .price(25.78f)
                .partNumber("12312312313123123123123")
                .unitOfMeasure(UnitOfMeasure.CENTIMETERS)
                        .creationDate("12.12.2005 12:34")
                .owner(Person.builder()
                        .eyeColor(Color.BLACK)
                        .hairColor(Color.GREEN)
                        .height(13L)
                        .location(Location.builder()
                                .z(50)
                                .x(50)
                                .y(50)
                                .build())
                        .name("test")
                        .build())
                .build()), "./test.csv");

        List<Product> products = csvFileWorker.read("./test.csv");
        products.forEach(product -> System.out.println(product.toString()));

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
