package ru.msaitov.service;

import org.junit.Before;
import org.junit.Test;
import ru.msaitov.WeatherDataTransfer;
import ru.msaitov.dao.WeatherDao;
import ru.msaitov.model.WeatherEntity;
import ru.msaitov.model.mapper.MapperWeather;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherServiceImplTest {

    private WeatherServiceImpl service;
    private WeatherDataTransfer weatherDataTransfer;
    private WeatherEntity weatherEntity;
    private MapperWeather mapperWeather = mock(MapperWeather.class);
    private WeatherDao weatherDao = mock(WeatherDao.class);


    @Before
    public void setValue() {
        service = new WeatherServiceImpl(mapperWeather, weatherDao);
        weatherEntity = new WeatherEntity();
        weatherDataTransfer = new WeatherDataTransfer();
        weatherDataTransfer.setCity("Moscow");
        weatherDataTransfer.setTemperature(2L);
        weatherEntity.setCity("Moscow");
        weatherEntity.setTemperature(2L);


    }

    @Test
    public void saveTest() {
        when(mapperWeather.map(weatherDataTransfer)).thenReturn(weatherEntity);
        service.save(weatherDataTransfer);
        verify(weatherDao).add(weatherEntity);
    }

    @Test
    public void loadLastTest() {
        when(weatherDao.getLastItem()).thenReturn(weatherEntity);
        when(mapperWeather.map(weatherEntity)).thenReturn(weatherDataTransfer);
        service.loadLast();
        verify(weatherDao).getLastItem();
        verify(mapperWeather).map(weatherEntity);
    }


}
