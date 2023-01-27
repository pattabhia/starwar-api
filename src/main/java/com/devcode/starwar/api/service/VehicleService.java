package com.devcode.starwar.api.service;

import com.devcode.starwar.api.models.Vehicle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("vehicles")
public class VehicleService extends AbstractAPIService<Vehicle> {

    public static final String VEHICLES = "vehicles";
    @Value("${star-war.api.url.vehicles}")
    private String url;

    @Override
    public Vehicle findCount() {
        return getWebClient()
                .get()
                .uri(uri())
                .retrieve()
                .bodyToMono(Vehicle.class)
                .block();
    }

    @Override
    public String getType() {
        return VEHICLES;
    }

    public String uri() {
        return url;
    }

    @Override
    public Vehicle searchByTypeAndName(String name) {
        return getWebClient()
                .get()
                .uri(uri(), uriBuilder -> uriBuilder.queryParam("search", name).build())
                .retrieve()
                .bodyToMono(Vehicle.class)
                .block();
    }

}
