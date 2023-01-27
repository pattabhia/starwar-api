package com.devcode.starwar.api.service;

import com.devcode.starwar.api.models.Planet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("planets")
public class PlanetService extends AbstractAPIService<Planet> {

    public static final String PLANETS = "planets";
    @Value("${star-war.api.url.planets}")
    private String url;

    @Override
    public Planet findCount() {
        return getWebClient()
                .get()
                .uri(uri())
                .retrieve()
                .bodyToMono(Planet.class)
                .block();
    }

    @Override
    public String getType() {
        return PLANETS;
    }

    public String uri() {
        return url;
    }

    @Override
    public Planet searchByTypeAndName(String name) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder.queryParam("search", name).build())
                .retrieve()
                .bodyToMono(Planet.class)
                .block();
    }

}
