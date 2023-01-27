package com.devcode.starwar.api.controller;

import com.devcode.starwar.api.domain.Domain;
import com.devcode.starwar.api.dto.TypeDTO;
import com.devcode.starwar.api.enums.Types;
import com.devcode.starwar.api.service.TypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class StarWarController {
    private final TypeService typeService;

    public StarWarController(TypeService typeService) {
        this.typeService = typeService;
    }

    @Tag(name = "types")
    @GetMapping("/types/")
    public ResponseEntity<Stream<Types>> findAllTypes() {
        return ResponseEntity.ok(Stream.of(Types.values()));
    }

    @Tag(name = "types")
    @GetMapping("/types/{type}/count")
    public ResponseEntity<TypeDTO> findCountByType(@PathVariable("type") Types type) {
        return ResponseEntity.ok(typeService.findCount(type));
    }

    @Tag(name = "types")
    @GetMapping("/do-search")
    public ResponseEntity<Domain> searchByTypeAndName(@RequestParam("type") Types type,
                                                      @RequestParam("name") String name) {
        return ResponseEntity.ok(typeService.searchByTypeAndName(type, name));
    }
}
