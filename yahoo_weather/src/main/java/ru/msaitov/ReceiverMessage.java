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

    @Inject
    private SenderObject senderData;

    @Inject
    private YahooGetWeather yahooGetWeather;

    /**
     * Слушатель, получаем название города, извлекаем данные о погоде и отправляем дальше
     * @param message
     */
    public void onMessage(Message message) {

        WeatherDataTransfer weatherDataTransfer = null;
        try {
            String cityName = message.getBody(String.class);
            weatherDataTransfer = yahooGetWeather.getCity(cityName);
        } catch (IOException | JMSException e) {
            e.printStackTrace();
        }
        senderData.send(weatherDataTransfer);
    }


}
