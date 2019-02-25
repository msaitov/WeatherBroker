package ru.msaitov.sendData;

import ru.msaitov.WeatherDataTransfer;

/**
 * Отправляем данные в другом модуль для дальнейшей обработке
 */
public interface SenderObject {

    /**
     * Отправляем данные, на вход аргумента данные для отправки
     * @param weatherDataTransfer
     */
    void send(WeatherDataTransfer weatherDataTransfer);
}
