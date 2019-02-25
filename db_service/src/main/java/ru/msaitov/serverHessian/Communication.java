package ru.msaitov.serverHessian;

import ru.msaitov.WeatherDataTransfer;

/**
 * Извлечение данных WeatherDataTransfer
 */
public interface Communication {

    /**
     * Извлечение данных WeatherDataTransfer, для передачи по протоколу http hessian
     * @return
     */
    WeatherDataTransfer run();
}
