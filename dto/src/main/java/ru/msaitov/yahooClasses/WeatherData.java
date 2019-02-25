package ru.msaitov.yahooClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Сериализация и Десериализация данных которые берутся с сервиса yahoo weather
 */
public class WeatherData implements Serializable {

    public Location location;

    @JsonProperty("current_observation")
    public CurrentObservation currentObservation;

    public List<Forecasts> forecasts;

}
