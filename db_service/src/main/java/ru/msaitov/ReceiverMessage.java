package ru.msaitov;

import ru.msaitov.service.WeatherService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Слушатель для получение данных о погоде
 */
@MessageDriven(name = "Receiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
        propertyValue = "java:jboss/exported/jms/WeatherData"),
        @ActivationConfigProperty(propertyName = "destinationType",
        propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
        propertyValue = "Auto-acknowledge")})
public class ReceiverMessage implements MessageListener {

    private WeatherService weatherService;

    @Inject
    public ReceiverMessage(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public ReceiverMessage() {
    }

    /**
     * Принимает данные для последующей обработке
     * @param message
     */
    public void onMessage(Message message) {

        if (message==null) {
            throw new RuntimeException("Parameter message is null");
        }

        WeatherDataTransfer weatherDataTransfer = null;
        try {
            weatherDataTransfer = message.getBody(WeatherDataTransfer.class);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
        weatherService.save(weatherDataTransfer);
    }





}
