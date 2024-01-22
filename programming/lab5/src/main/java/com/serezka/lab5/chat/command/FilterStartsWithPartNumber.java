package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class FilterStartsWithPartNumber extends Command {
    public FilterStartsWithPartNumber() {
        super("filter_starts_with_part_number \\d+", "вывести элементы, значение поля partNumber которых начинается с заданной подстроки");
    }

    @Override
    public void execute(Chat chat, Update update) {
        // todo
    }
}
