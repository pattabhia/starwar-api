package com.devcode.starwar.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "episode_id",
        "opening_crawl",
        "director",
        "producer",
        "release_date",
        "characters",
        "planets",
        "starships",
        "vehicles",
        "species",
        "created",
        "edited",
        "url"
})
@Data
public class FilmResult {
    @JsonProperty("title")
    private String title;
    @JsonProperty("episode_id")
    private int episodeId;
    @JsonProperty("opening_crawl")
    @JsonIgnore
    private String openingCrawl;
    @JsonProperty("director")
    private String director;
    @JsonProperty("producer")
    private String producer;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("characters")
    @JsonIgnore
    private List<String> characters;
    @JsonProperty("planets")
    @JsonIgnore
    private List<String> planets;
    @JsonProperty("starships")
    @JsonIgnore
    private List<String> starships;
    @JsonProperty("vehicles")
    @JsonIgnore
    private List<String> vehicles;
    @JsonProperty("species")
    @JsonIgnore
    private List<String> species;
    @JsonProperty("created")
    @JsonIgnore
    private String created;
    @JsonProperty("edited")
    @JsonIgnore
    private String edited;
    @JsonProperty("url")
    @JsonIgnore
    private String url;
}