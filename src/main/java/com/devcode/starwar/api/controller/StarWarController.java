package com.devcode.starwar.api.controller;

import com.devcode.starwar.api.dto.TypeDTO;
import com.devcode.starwar.api.enums.Types;
import com.devcode.starwar.api.models.Domain;
import com.devcode.starwar.api.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@Slf4j
public class StarWarController {
    private final TypeService typeService;

    public StarWarController(TypeService typeService) {
        this.typeService = typeService;
    }

    @Tag(name = "types")
    @GetMapping("/types/")
    @Operation(
            summary = "list of all types",
            description = "list of all types",
            tags = { "types" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Types.class))
                    ),
            }
    )
    public ResponseEntity<Stream<Types>> findAllTypes() {
        return ResponseEntity.ok(Stream.of(Types.values()));
    }

    @Tag(name = "types")
    @GetMapping("/types/{type}/count")
    @Operation(
            summary = "find count of records by type",
            description = "find count of records by type",
            tags = { "types" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Domain.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<TypeDTO> findCountByType(@PathVariable("type") Types type) {
        return ResponseEntity.ok(typeService.findCount(type));
    }

    @Tag(name = "types")
    @GetMapping(value = "/do-search", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "search by type and name",
            description = "search by type and name",
            tags = { "types" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Domain.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Domain> searchByTypeAndName(@Valid @RequestParam("type") Types type,
                                                      @RequestParam("name") String name) {
        return ResponseEntity.ok(typeService.searchByTypeAndName(type, name));
    }
}