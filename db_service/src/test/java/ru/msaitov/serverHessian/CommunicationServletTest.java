package ru.msaitov.serverHessian;

import org.junit.Before;
import org.junit.Test;
import ru.msaitov.WeatherDataTransfer;
import ru.msaitov.service.WeatherService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommunicationServletTest {

    private CommunicationServlet communicationServlet;
    private WeatherService weatherService = mock(WeatherService.class);
    private WeatherDataTransfer weatherDataTransfer = new WeatherDataTransfer();


    @Before
    public void setValue() {
        weatherDataTransfer.setCity("Kazan");
        weatherDataTransfer.setTemperature(5L);
        communicationServlet = new CommunicationServlet(weatherService);
        when(weatherService.loadLast()).thenReturn(weatherDataTransfer);
    }

    @Test
    public void runTest() {
        communicationServlet.run();
        verify(weatherService).loadLast();
    }


}
