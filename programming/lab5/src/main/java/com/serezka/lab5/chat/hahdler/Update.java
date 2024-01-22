package com.serezka.lab5.chat.hahdler;

import com.serezka.lab5.chat.obj.Product;
import com.serezka.lab5.chat.user.UserData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.LinkedList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter @AllArgsConstructor
public class Update {
    String message;
}
