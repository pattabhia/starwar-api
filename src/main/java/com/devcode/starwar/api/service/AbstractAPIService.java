package com.devcode.starwar.api.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Getter
@Service
public abstract class AbstractAPIService<R> implements APIService<R> {

    @Autowired private WebClient webClient;

    public abstract R findCount();

    public abstract String getType();
}
