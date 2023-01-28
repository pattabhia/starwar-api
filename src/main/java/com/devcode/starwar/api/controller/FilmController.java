package com.devcode.starwar.api.controller;

import com.devcode.starwar.api.models.Film;
import com.devcode.starwar.api.models.FilmResult;
import com.devcode.starwar.api.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @Tag(name = "films")
    @GetMapping("/films/names/{name}")
    @Operation(
            summary = "find films by name",
            description = "find films by name",
            tags = { "films" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Film.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Film> findFilmByName(@PathVariable("name") String name) {
        //return ResponseEntity.ok(filmService.findFilmByName(name)); //using webclient
        return ResponseEntity.ok(filmService.doSearchByName(name)); //using rest-template
    }

    @Tag(name = "films")
    @GetMapping("/films/{id}")
    @Operation(
            summary = "find films by id",
            description = "find films by id",
            tags = { "films" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FilmResult.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<FilmResult> findFilmsById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(filmService.findFilmById(id));
    }
}