package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class FilterStartsWithPartNumber extends Command {
    public FilterStartsWithPartNumber() {
        super("filter_starts_with_part_number {part_number}", "filter_starts_with_part_number .+", "вывести элементы, значение поля partNumber которых начинается с заданной подстроки");
    }

    @Override
    public void execute(Bridge bridge) {
//        final String data = bridge.getUpdate().getMessage().split(" ", 2)[1];

        // todo
//        bridge.addNestedProducts(bridge.getData().stream()
//                .filter(product -> product.getPartNumber().startsWith(data))
//                .toList());
    }
}
