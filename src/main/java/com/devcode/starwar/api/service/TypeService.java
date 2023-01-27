package com.devcode.starwar.api.service;

import com.devcode.starwar.api.models.Domain;
import com.devcode.starwar.api.dto.TypeDTO;
import com.devcode.starwar.api.enums.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    @Autowired
    private ServiceFactory serviceFactory;

    public TypeDTO findCount(Types types) {
        Domain count = serviceFactory.getService(types).findCount();
        return new TypeDTO(types.name(), count.getCount());
    }

    public Domain searchByTypeAndName(Types types, String name) {
        return serviceFactory.getService(types).searchByTypeAndName(name);
    }
}
