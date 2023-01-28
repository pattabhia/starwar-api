package com.devcode.starwar.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.BAD_REQUEST)
@NoArgsConstructor
@Data
public class RequiredFieldsMissingException extends RuntimeException  {

    public RequiredFieldsMissingException(String msg) {
        super(msg);
    }
}
