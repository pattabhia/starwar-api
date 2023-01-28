package com.devcode.starwar.api.service;

import com.devcode.starwar.api.exception.ErrorMessage;
import com.devcode.starwar.api.exception.ResourceNotFoundException;
import com.devcode.starwar.api.exception.TechnicalException;
import com.devcode.starwar.api.models.Film;
import com.devcode.starwar.api.models.FilmResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> map = new HashMap<>();
        map.put("search", name);
        return getRestTemplate().getForObject(uri(), Film.class, map);
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
                .onStatus(HttpStatusCode::is4xxClientError, res -> res.bodyToMono(ErrorMessage.class)
                        .onErrorResume(e -> Mono.error(new ResourceNotFoundException("resource not found")))
                        .flatMap(errorBody -> Mono.error(new ResourceNotFoundException(errorBody.getMessage())))
                )
                .onStatus(HttpStatusCode::is5xxServerError, res -> res.bodyToMono(ErrorMessage.class)
                        .onErrorResume(e -> Mono.error(new TechnicalException("internal server error")))
                        .flatMap(errorBody -> Mono.error(new TechnicalException(errorBody.getMessage())))
                )
                .bodyToMono(FilmResult.class)
                .block();
    }

    public Film doSearchByName(String name) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder.queryParam("search", name).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, res -> res.bodyToMono(ErrorMessage.class)
                        .onErrorResume(e -> Mono.error(new ResourceNotFoundException("resource not found")))
                        .flatMap(errorBody -> Mono.error(new ResourceNotFoundException(errorBody.getMessage())))
                )
                .onStatus(HttpStatusCode::is5xxServerError, res -> res.bodyToMono(ErrorMessage.class)
                        .onErrorResume(e -> Mono.error(new TechnicalException("internal server error")))
                        .flatMap(errorBody -> Mono.error(new TechnicalException(errorBody.getMessage())))
                )
                .bodyToMono(Film.class)
                .block();
    }
}