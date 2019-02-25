package ru.msaitov.model.mapper;

import ru.msaitov.WeatherDataTransfer;
import ru.msaitov.model.WeatherEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class MapperWeatherImpl implements MapperWeather {
    @Override
    public WeatherDataTransfer map(WeatherEntity weatherEntity) {
        WeatherDataTransfer weatherDataTransfer = new WeatherDataTransfer();
        weatherDataTransfer.setCity(weatherEntity.getCity().trim());
        weatherDataTransfer.setTemperature(weatherEntity.getTemperature());
        return weatherDataTransfer;
    }

    @Override
    public WeatherEntity map(WeatherDataTransfer weatherDataTransfer) {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCity(weatherDataTransfer.getCity().trim());
        weatherEntity.setTemperature(weatherDataTransfer.getTemperature());
        return weatherEntity;
    }
}
