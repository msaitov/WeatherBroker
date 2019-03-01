package ru.msaitov;

import ru.msaitov.sendData.SenderObject;
import ru.msaitov.yahooData.YahooGetWeather;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;

/**
 * Слушатель
 */
@MessageDriven(name = "Receiver", activationConfig = {

        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:jboss/exported/jms/CityName"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")})

public class ReceiverMessage implements MessageListener {

    private SenderObject senderObject;

    private YahooGetWeather yahooGetWeather;

    @Inject
    public ReceiverMessage(final SenderObject senderObject, final YahooGetWeather yahooGetWeather) {
        this.senderObject = senderObject;
        this.yahooGetWeather = yahooGetWeather;
    }

    public ReceiverMessage() {
    }

    /**
     * Слушатель, получаем название города, извлекаем данные о погоде и отправляем дальше
     * @param message
     */
    public void onMessage(final Message message) {

        if (message==null) {
            throw new RuntimeException("Parameter message is null");
        }

        WeatherDataTransfer weatherDataTransfer = null;
        try {
            String cityName = message.getBody(String.class);
            weatherDataTransfer = yahooGetWeather.getDataWeather(cityName);
        } catch (IOException | JMSException e) {
            throw new RuntimeException(e);
        }
        senderObject.send(weatherDataTransfer);
    }


}
