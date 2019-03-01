package ru.msaitov.serverHessian;

import com.caucho.hessian.server.HessianServlet;
import ru.msaitov.WeatherDataTransfer;
import ru.msaitov.service.WeatherService;

import javax.inject.Inject;

/**
 * Сервлет Hessian
 */
public class CommunicationServlet extends HessianServlet implements Communication {

    private final WeatherService weatherService;

    @Inject
    public CommunicationServlet(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherDataTransfer run() {
        WeatherDataTransfer weatherDataTransfer = weatherService.loadLast();
        return weatherDataTransfer;
    }
}
