package com.devcode.starwar.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDTO {

    private String type;
    @JsonProperty("totalCount")
    private Integer count;

}
