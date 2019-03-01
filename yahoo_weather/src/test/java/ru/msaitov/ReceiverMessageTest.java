package ru.msaitov;

import org.junit.Before;
import org.junit.Test;
import ru.msaitov.sendData.SenderObject;
import ru.msaitov.yahooData.YahooGetWeather;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReceiverMessageTest {

    private ReceiverMessage receiverMessage;
    private WeatherDataTransfer weatherDataTransfer;
    private SenderObject senderObject = mock(SenderObject.class);
    private YahooGetWeather yahooGetWeather = mock(YahooGetWeather.class);
    private Message message = mock(Message.class);



    @Before
    public void setValue() throws IOException, JMSException {
        weatherDataTransfer = new WeatherDataTransfer();
        weatherDataTransfer.setCity("Moscow");
        weatherDataTransfer.setTemperature(1L);
        receiverMessage = new ReceiverMessage(senderObject, yahooGetWeather);
        when(message.getBody(String.class)).thenReturn("Moscow");
        when(yahooGetWeather.getDataWeather("Moscow")).thenReturn(weatherDataTransfer);

    }

    @Test
    public void onMessageTest() {
        receiverMessage.onMessage(message);
        verify(senderObject).send(weatherDataTransfer);
    }


}
