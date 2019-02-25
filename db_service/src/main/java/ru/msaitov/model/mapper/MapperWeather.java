package ru.msaitov.model.mapper;

import ru.msaitov.WeatherDataTransfer;
import ru.msaitov.model.WeatherEntity;

/**
 * Маппер между классами Entity и WeatherDataTransfer
 */
public interface MapperWeather {

    /**
     * Принимаем в аргументе Entity возвращаем WeatherDataTransfer
     * @param weatherEntity
     * @return
     */
    WeatherDataTransfer map(WeatherEntity weatherEntity);

    /**
     * Принимаем в аргументе WeatherDataTransfer возвращаем Entity
     * @param weatherDataTransfer
     * @return
     */
    WeatherEntity map(WeatherDataTransfer weatherDataTransfer);

}
