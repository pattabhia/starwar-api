package com.devcode.starwar.api.service;

import com.devcode.starwar.api.domain.Domain;
import com.devcode.starwar.api.enums.Types;
import org.springframework.stereotype.Component;

@Component
public interface ServiceFactory {
    APIService<? extends Domain> getService(Types types);
}
