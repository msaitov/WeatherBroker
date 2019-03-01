package ru.msaitov.sendData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.msaitov.WeatherDataTransfer;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class SenderObjectImplTest {

    private SenderObjectImpl sender;
    private WeatherDataTransfer weatherDataTransfer;
    private JMSProducer jmsProducer = mock(JMSProducer.class);
    private JMSContext jmsContext = mock(JMSContext.class);
    private Queue queue = mock(Queue.class);

    @Before
    public void setValue() {
        sender = new SenderObjectImpl(jmsContext);
        weatherDataTransfer = new WeatherDataTransfer();
        sender.setQueue(queue);
        weatherDataTransfer.setCity("Moscow");
        weatherDataTransfer.setTemperature(1L);
        when(jmsContext.createProducer()).thenReturn(jmsProducer);

    }

    @Test
    public void sendTest1() {
        sender.send(weatherDataTransfer);
        Mockito.verify(jmsContext.createProducer(), times(1)).send(queue, weatherDataTransfer);
    }

    @Test
    public void sendTest2() {
        sender.send(null);
        Mockito.verify(jmsContext.createProducer(), times(0)).send(queue, weatherDataTransfer);
    }


}
