package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class RequestSample {
    private String id;
    private String name;
    private String something;

    public RequestSample() {
    }

    @JsonCreator
    public RequestSample(@JsonProperty("id") String id,
                         @JsonProperty("name") String name,
                         @JsonProperty("something") String something) {
        this.id = id;
        this.name = name;
        this.something = something;
    }

    @JsonGetter("id")
    public String getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("something")
    public String getSomething() {
        return something;
    }

    @JsonSetter("something")
    public void setSomething(String something) {
        this.something = something;
    }
}
