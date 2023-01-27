package com.devcode.starwar.api.controller;

import com.devcode.starwar.api.models.Film;
import com.devcode.starwar.api.models.FilmResult;
import com.devcode.starwar.api.service.FilmService;
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
    public ResponseEntity<Film> findFilmByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(filmService.findFilmByName(name));
    }

    @Tag(name = "films")
    @GetMapping("/films/{id}")
    public ResponseEntity<FilmResult> findFilmsById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(filmService.findFilmById(id));
    }
}