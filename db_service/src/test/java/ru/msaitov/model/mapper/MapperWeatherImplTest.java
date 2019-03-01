package ru.msaitov.model.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.msaitov.WeatherDataTransfer;
import ru.msaitov.model.WeatherEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.samePropertyValuesAs;

public class MapperWeatherImplTest {

    private MapperWeatherImpl mapperWeather = new MapperWeatherImpl();
    private WeatherEntity weatherEntity = new WeatherEntity();
    private WeatherDataTransfer weatherDataTransfer = new WeatherDataTransfer();

    @Before
    public void setValue() {
        weatherEntity.setCity("Omsk");
        weatherEntity.setTemperature(5L);
        weatherDataTransfer.setCity("Omsk");
        weatherDataTransfer.setTemperature(5L);
    }


    @Test
    public void mapTest1() {
        WeatherEntity weatherEntity = mapperWeather.map(weatherDataTransfer);
        assertThat(this.weatherEntity, samePropertyValuesAs(weatherEntity));
    }

    @Test
    public void mapTest2() {
        WeatherDataTransfer weatherDataTransfer = mapperWeather.map(weatherEntity);
        assertThat(this.weatherDataTransfer, samePropertyValuesAs(weatherDataTransfer));
    }


}
