package ru.msaitov.service;

import org.springframework.transaction.annotation.Transactional;
import ru.msaitov.WeatherDataTransfer;
import ru.msaitov.dao.WeatherDao;
import ru.msaitov.model.WeatherEntity;
import ru.msaitov.model.mapper.MapperWeather;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class WeatherServiceImpl implements WeatherService {

    private final MapperWeather mapperWeather;

    private final WeatherDao weatherDao;

    @Inject
    public WeatherServiceImpl(MapperWeather mapperWeather, WeatherDao weatherDao) {
        this.mapperWeather = mapperWeather;
        this.weatherDao = weatherDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(WeatherDataTransfer weatherDataTransfer) {
        WeatherEntity weatherEntity = mapperWeather.map(weatherDataTransfer);
        weatherDao.add(weatherEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public WeatherDataTransfer loadLast() {
        WeatherEntity weatherEntity = weatherDao.getLastItem();
        WeatherDataTransfer weatherDataTransfer = mapperWeather.map(weatherEntity);
        return weatherDataTransfer;
    }
}
