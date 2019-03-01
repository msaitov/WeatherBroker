package ru.msaitov.sender;

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
public class SenderImpl implements Sender {

    private Queue queue;

    private final JMSContext context;

    @Inject
    public SenderImpl(JMSContext context) {
        this.context = context;
    }

    @Resource(mappedName = "java:jboss/exported/jms/CityName")
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendCity(String cityName) {
        if ((cityName==null) || cityName.equals("")) {
            throw new RuntimeException("Parameter cityName is null or empty");
        }
        cityName = cityName.trim();
        JMSProducer jmsProducer = null;
        jmsProducer = context.createProducer().send(queue, cityName);
    }
}