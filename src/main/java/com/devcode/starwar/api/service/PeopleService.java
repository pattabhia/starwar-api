package com.devcode.starwar.api.service;

import com.devcode.starwar.api.domain.Film;
import com.devcode.starwar.api.domain.People;
import com.devcode.starwar.api.domain.PeopleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("people")
public class PeopleService extends AbstractAPIService<People> {

    public static final String PEOPLE = "people";
    @Value("${star-war.api.url.people}")
    private String url;

    @Autowired
    private FilmService filmService;

    public People findCount() {
        return getWebClient()
                .get()
                .uri(uri())
                .retrieve()
                .bodyToMono(People.class)
                .block();
    }

    public PeopleResult findPeopleById(Integer id) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder
                        .path("{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(PeopleResult.class)
                .block();
    }

    @Override
    public String getType() {
        return PEOPLE;
    }

    public String uri() {
        return url;
    }

    @Override
    public People searchByTypeAndName(String name) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder.queryParam("search", name).build())
                .retrieve()
                .bodyToMono(People.class)
                .block();
    }

    public People findPeopleByName(String name) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder
                        .queryParam("search", name)
                        .build())
                .retrieve()
                .bodyToMono(People.class)
                .block();
    }
}
