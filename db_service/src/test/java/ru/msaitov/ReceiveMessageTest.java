package ru.msaitov;


import org.junit.Before;
import org.junit.Test;
import ru.msaitov.service.WeatherService;


import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ReceiveMessageTest {

    private ReceiverMessage receiverMessage;
    private WeatherService weatherService = mock(WeatherService.class);
    private WeatherDataTransfer weatherDataTransfer;
    private Message message = mock(Message.class);

    @Before
    public void setValue() throws JMSException {
        receiverMessage = new ReceiverMessage(weatherService);
        weatherDataTransfer = new WeatherDataTransfer();
        weatherDataTransfer.setCity("Moscow");
        weatherDataTransfer.setTemperature(1L);
        when(message.getBody(WeatherDataTransfer.class)).thenReturn(weatherDataTransfer);
    }

    @Test
    public void onMessageTest1() {
        receiverMessage.onMessage(message);
        verify(weatherService,times(1)).save(weatherDataTransfer);
    }

    @Test(expected = RuntimeException.class)
    public void onMessageTest2() {
        receiverMessage.onMessage(null);
    }


}
