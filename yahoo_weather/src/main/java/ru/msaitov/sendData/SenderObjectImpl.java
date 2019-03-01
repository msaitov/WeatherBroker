package ru.msaitov.sendData;

import ru.msaitov.WeatherDataTransfer;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class SenderObjectImpl implements SenderObject {

    private JMSContext context;

    private Queue queue;

    @Inject
    public SenderObjectImpl(JMSContext context) {
        this.context = context;
    }

    @Resource(mappedName = "java:jboss/exported/jms/WeatherData")
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(WeatherDataTransfer weatherDataTransfer) {
        JMSProducer jmsProducer = null;
        if (weatherDataTransfer != null) {
            jmsProducer = context.createProducer().send(queue, weatherDataTransfer);
        }
    }
}
