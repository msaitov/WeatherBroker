package ru.msaitov.yahooClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    public Long woeid;

    public String city;

    public String region;

    public String country;

    public Double lat;

    @JsonProperty("long")
    public Double longg;

    @JsonProperty("timezone_id")
    public String timezoneId;

}

