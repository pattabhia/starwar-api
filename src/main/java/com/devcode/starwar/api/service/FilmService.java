package com.devcode.starwar.api.service;

import com.devcode.starwar.api.models.Film;
import com.devcode.starwar.api.models.FilmResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("films")
public class FilmService extends AbstractAPIService<Film> {

    public static final String FILMS = "films";

    @Value("${star-war.api.url.films}")
    private String url;

    @Override
    public Film findCount() {
        return getWebClient()
                .get()
                .uri(uri())
                .retrieve()
                .bodyToMono(Film.class)
                .block();
    }

    @Override
    public String getType() {
        return FILMS;
    }

    public String uri() {
        return url;
    }

    @Override
    public Film searchByTypeAndName(String name) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder.queryParam("search", name).build())
                .retrieve()
                .bodyToMono(Film.class)
                .block();
    }

    public Film findFilmByName(String name) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder
                        .queryParam("search", name)
                        .build())
                .retrieve()
                .bodyToMono(Film.class)
                .block();
    }

    public FilmResult findFilmById(Integer id) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder
                        .path("{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(FilmResult.class)
                .block();
    }
}