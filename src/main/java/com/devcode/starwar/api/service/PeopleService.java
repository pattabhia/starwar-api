package com.devcode.starwar.api.service;

import com.devcode.starwar.api.exception.ErrorMessage;
import com.devcode.starwar.api.exception.ResourceNotFoundException;
import com.devcode.starwar.api.exception.TechnicalException;
import com.devcode.starwar.api.models.People;
import com.devcode.starwar.api.models.PeopleResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("people")
public class PeopleService extends AbstractAPIService<People> {

    public static final String PEOPLE = "people";
    @Value("${star-war.api.url.people}")
    private String url;


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
                .onStatus(HttpStatusCode::is4xxClientError, res -> res.bodyToMono(ErrorMessage.class)
                        .onErrorResume(e -> Mono.error(new ResourceNotFoundException("resource not found")))
                        .flatMap(errorBody -> Mono.error(new ResourceNotFoundException(errorBody.getMessage())))
                )
                .onStatus(HttpStatusCode::is5xxServerError, res -> res.bodyToMono(ErrorMessage.class)
                        .onErrorResume(e -> Mono.error(new TechnicalException("internal server error")))
                        .flatMap(errorBody -> Mono.error(new TechnicalException(errorBody.getMessage())))
                )
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