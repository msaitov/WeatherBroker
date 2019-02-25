package ru.msaitov.sender;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class Sender implements SenderImpl {

    @Resource(mappedName = "java:jboss/exported/jms/CityName")
    private Queue queue;

    @Inject
    private JMSContext context;


    /**
     * {@inheritDoc}
     */
    @Override
    public void sendCity(String txt) {
        context.createProducer().send(queue, txt);
    }
}