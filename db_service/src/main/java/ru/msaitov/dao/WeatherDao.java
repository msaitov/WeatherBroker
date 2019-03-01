package ru.msaitov.dao;

import ru.msaitov.model.WeatherEntity;

/**
 * DAO для работы с БД (базы данных) о погоде
 */
public interface WeatherDao {

    /**
     * Добавление в БД данные о погоде
     * @param weatherEntity
     */
    void add(WeatherEntity weatherEntity);

    /**
     * Извлечение из БД последние данные о погоде
     * @return
     */
    WeatherEntity getLastItem();
}
