package com.devcode.starwar.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "count",
        "next",
        "previous",
        "results"
})
@Data
@NoArgsConstructor
public class Specie implements Domain {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("next")
    private Object next;
    @JsonProperty("previous")
    private Object previous;
    @JsonProperty("results")
    private List<SpecieResult> results;

}
