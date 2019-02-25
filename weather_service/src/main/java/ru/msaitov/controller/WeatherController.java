package ru.msaitov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.msaitov.hessian.Communication;
import ru.msaitov.WeatherDataTransfer;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер данных о погоде
 */
@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class WeatherController  {

    private HessianProxyFactoryBean factory;

    @Autowired
    public WeatherController(HessianProxyFactoryBean factory) {
        this.factory = factory;
    }

    /**
     * Получение данных о погоде
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<WeatherDataTransfer> getData() {
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Communication communication = (Communication) factory.getObject();
        WeatherDataTransfer weatherDataTransfer = communication.run();
        return new ResponseEntity<WeatherDataTransfer>(weatherDataTransfer, httpHeaders, HttpStatus.OK);
    }



}
