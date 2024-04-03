package com.serezka.server.collection.controller;

import com.serezka.server.authorization.database.model.User;
import com.serezka.server.collection.database.model.Flat;
import com.serezka.server.collection.database.service.FlatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// filter chain /collection/** and /execute/**

@RequestMapping("/collection")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CollectionController {
    FlatService flatService;

    @GetMapping("/all")
    public ResponseEntity<List<Flat>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(flatService.findAll().stream()
                .peek(flat -> flat.setEditable(flat.getUser().equals(user))).toList());
    }

    @GetMapping("/size")
    public ResponseEntity<Integer> findAllSize() {
        return ResponseEntity.ok(flatService.findAll().size());
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clear(@AuthenticationPrincipal User user) {
        flatService.removeAllByUserId(user.getId());
        return ResponseEntity.ok("Collection is cleared");
    }

    @PostMapping("/add")
    public ResponseEntity<Flat> add(@AuthenticationPrincipal User user,
                                    @RequestBody Flat flat) {
        return ResponseEntity.ok(flatService.save(flat, user));
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<Flat>> addAll(@AuthenticationPrincipal User user,
                                             @RequestBody List<Flat> flats) {
        return ResponseEntity.ok(flatService.saveAll(flats, user));
    }

    @PostMapping("/update")
    public ResponseEntity<Flat> update(@AuthenticationPrincipal User user,
                                       @RequestBody Flat flat) {
        return ResponseEntity.ok(flatService.save(flat, user));
    }

    @PostMapping("/remove")
    public ResponseEntity<String> remove(@AuthenticationPrincipal User user,
                                         @RequestParam Long id) {
        flatService.removeByIdAndUserId(id, user.getId());
        return ResponseEntity.ok("Flat is removed");
    }
}
