package com.devcode.starwar.api.service;

import com.devcode.starwar.api.domain.Film;
import com.devcode.starwar.api.domain.Planet;
import com.devcode.starwar.api.domain.Specie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("species")
public class SpeciesService extends AbstractAPIService<Specie> {

    public static final String SPECIES = "species";
    @Value("${star-war.api.url.species}")
    private String url;

    @Override
    public Specie findCount() {
        return getWebClient()
                .get()
                .uri(uri())
                .retrieve()
                .bodyToMono(Specie.class)
                .block();
    }

    @Override
    public String getType() {
        return SPECIES;
    }

    public String uri() {
        return url;
    }

    @Override
    public Specie searchByTypeAndName(String name) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder.queryParam("search", name).build())
                .retrieve()
                .bodyToMono(Specie.class)
                .block();
    }
}
