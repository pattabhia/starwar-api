package com.devcode.starwar.api.controller;

import com.devcode.starwar.api.models.People;
import com.devcode.starwar.api.models.PeopleResult;
import com.devcode.starwar.api.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Tag(name = "people")
    @GetMapping("/names/{name}")
    @Operation(
            summary = "find people by name",
            description = "find people by name",
            tags = { "people" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = People.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<People> findPeopleByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(peopleService.findPeopleByName(name));
    }

    @Tag(name = "people")
    @GetMapping("/{id}")
    @Operation(
            summary = "find people by id",
            description = "find people by id",
            tags = { "people" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PeopleResult.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<PeopleResult> findPeopleById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(peopleService.findPeopleById(id));
    }
}