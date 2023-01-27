package com.devcode.starwar.api.service;

import com.devcode.starwar.api.models.StarShip;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("starships")
public class StarShipService extends AbstractAPIService<StarShip> {

    public static final String STARSHIPS = "starships";
    @Value("${star-war.api.url.starships}")
    private String url;

    @Override
    public StarShip findCount() {
        return getWebClient()
                .get()
                .uri(uri())
                .retrieve()
                .bodyToMono(StarShip.class)
                .block();
    }

    @Override
    public String getType() {
        return STARSHIPS;
    }

    public String uri() {
        return url;
    }

    @Override
    public StarShip searchByTypeAndName(String name) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder.queryParam("search", name).build())
                .retrieve()
                .bodyToMono(StarShip.class)
                .block();
    }

}
