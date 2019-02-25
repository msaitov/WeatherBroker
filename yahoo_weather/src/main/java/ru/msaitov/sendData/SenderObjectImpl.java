package ru.msaitov.sendData;

import ru.msaitov.WeatherDataTransfer;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class SenderObjectImpl implements SenderObject {

    @Resource(mappedName = "java:jboss/exported/jms/WeatherData")
    private Queue queue;

    @Inject
    private JMSContext context;

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(WeatherDataTransfer weatherDataTransfer) {
        context.createProducer().send(queue, weatherDataTransfer);
    }


}
