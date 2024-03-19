package com.serezka.server.collection.controller;

import com.serezka.server.collection.database.model.Flat;
import com.serezka.server.collection.database.service.FlatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/collection")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CollectionController {
    FlatService flatService;

    @GetMapping("/all")
    public ResponseEntity<List<Flat>> findAll() {
        return ResponseEntity.ok(flatService.findAll());
    }

}
