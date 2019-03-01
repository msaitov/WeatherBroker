package ru.msaitov.yahooData;

import ru.msaitov.WeatherDataTransfer;

import java.io.IOException;

/**
 * Получение данных с сервиса yahoo weather
 */
public interface YahooGetWeather {

    /**
     * Получение данных о погоде по названию города
     * @param cityJSP
     * @return
     * @throws IOException
     */
    WeatherDataTransfer getDataWeather(String cityJSP) throws IOException;
}
