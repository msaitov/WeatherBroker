package ru.msaitov;

import java.io.Serializable;

/**
 * Хранение данных о погоде для передачи между модулями
 */
public class WeatherDataTransfer implements Serializable {

    private String city;
    private Long temperature;

    public WeatherDataTransfer() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getTemperature() {
        return temperature;
    }

    public void setTemperature(Long temperature) {
        this.temperature = temperature;
    }
}
