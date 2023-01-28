package com.devcode.starwar.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
@Data
public class ResourceNotFoundException extends RuntimeException  {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
