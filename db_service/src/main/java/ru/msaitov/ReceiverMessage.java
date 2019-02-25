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

    @Inject
    private WeatherService weatherService;

    /**
     * Принимает данные для последующей обработке
     * @param message
     */
    public void onMessage(Message message) {
        WeatherDataTransfer weatherDataTransfer = null;
        try {
            weatherDataTransfer = message.getBody(WeatherDataTransfer.class);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        weatherService.save(weatherDataTransfer);
    }





}
