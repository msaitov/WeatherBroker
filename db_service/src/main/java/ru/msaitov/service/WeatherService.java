package ru.msaitov.service;

import ru.msaitov.WeatherDataTransfer;

/**
 * Сервис для работы с данными погоды
 */
public interface WeatherService {

    /**
     * Сохранение данных о погоде
     * @param weatherDataTransfer
     */
    void save(WeatherDataTransfer weatherDataTransfer);

    /**
     * Извлечение данных о погоде
     * @return
     */
    WeatherDataTransfer loadLast();

}
