package com.serezka.server.collection.controller;

import com.serezka.server.authorization.database.model.User;
import com.serezka.server.collection.execution.handler.Handler;
import com.serezka.server.collection.execution.transfer.Request;
import com.serezka.server.collection.execution.transfer.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ExecutionController {
    Handler handler;

    @PostMapping("/execute")
    public ResponseEntity<Response> execute(@RequestBody Request request,
                                            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(handler.handle(request, user));
    }

}
