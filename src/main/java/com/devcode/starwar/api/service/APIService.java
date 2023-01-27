package com.devcode.starwar.api.service;

public interface APIService<R> {

    R findCount();
    String uri();
    R searchByTypeAndName(String name);
}
