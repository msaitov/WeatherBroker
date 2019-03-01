package ru.msaitov.yahooData;

import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.msaitov.WeatherDataTransfer;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class YahooGetWeatherImplTest {

    private YahooGetWeather yahooGetWeather;

    @Before
    public void setValue() {
        yahooGetWeather = new YahooGetWeatherImpl();
    }

    @Test
    public void getDataWeather1() throws IOException {
        WeatherDataTransfer weatherDataTransfer = yahooGetWeather.getDataWeather("Moscow");
        String cityName = weatherDataTransfer.getCity();
        assertEquals(cityName,"Moscow");
    }

    @Test(expected = RuntimeException.class)
    public void getDataWeather2() throws IOException {
        WeatherDataTransfer weatherDataTransfer = yahooGetWeather.getDataWeather("");
    }


}
