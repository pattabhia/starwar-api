package com.devcode.starwar.api.controller;

import com.devcode.starwar.api.domain.People;
import com.devcode.starwar.api.domain.PeopleResult;
import com.devcode.starwar.api.service.PeopleService;
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
    public ResponseEntity<People> findPeopleByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(peopleService.findPeopleByName(name));
    }

    @Tag(name = "people")
    @GetMapping("/{id}")
    public ResponseEntity<PeopleResult> findPeopleById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(peopleService.findPeopleById(id));
    }
}