package ru.msaitov;

import org.junit.Before;
import org.junit.Test;
import ru.msaitov.sender.SenderImpl;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SenderImplTest {

    private SenderImpl sender;
    private JMSContext jmsContext = mock(JMSContext.class);
    private JMSProducer jmsProducer = mock(JMSProducer.class);
    private Queue queue = mock(Queue.class);

    @Before
    public void setValue() {
        sender = new SenderImpl(jmsContext);
        sender.setQueue(queue);
        when(jmsContext.createProducer()).thenReturn(jmsProducer);
    }

    @Test
    public void sendCityTest1() {
        sender.sendCity("Moscow");
        verify(jmsProducer,times(1)).send(queue,"Moscow");

    }

    @Test(expected = RuntimeException.class)
    public void sendCityTest2() {
        sender.sendCity("");
        verify(jmsProducer,times(0)).send(queue,"Moscow");
    }

}
