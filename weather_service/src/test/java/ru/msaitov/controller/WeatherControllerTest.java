package ru.msaitov.controller;

import org.junit.Test;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import ru.msaitov.WeatherDataTransfer;
import ru.msaitov.hessian.Communication;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherControllerTest {

    private HessianProxyFactoryBean factory = mock(HessianProxyFactoryBean.class);
    private WeatherController controller = new WeatherController(factory);
    private WeatherDataTransfer weatherDataTransfer = new WeatherDataTransfer();
    private Communication communication = mock(Communication.class);

    @Test
    public void getDataTest() {
        when(factory.getObject()).thenReturn(communication);
        when(communication.run()).thenReturn(weatherDataTransfer);
        controller.getData();
        verify(communication).run();
    }



}
