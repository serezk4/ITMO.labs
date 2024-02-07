package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SumOfNumberOfRooms extends Command {
    FlatService flatService;

    public SumOfNumberOfRooms(FlatService flatService) {
        super("sum_of_number_of_rooms", "sum_of_number_of_rooms", "вывести сумму значений поля numberOfRooms для всех элементов коллекции");
        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("Итог: %d",
                flatService.findAllByUserId(bridge.getUserId()).stream()
                        .mapToInt(Flat::getNumberOfRooms).sum());
    }
}
