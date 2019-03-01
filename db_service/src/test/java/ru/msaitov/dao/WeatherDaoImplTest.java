package ru.msaitov.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.msaitov.model.WeatherEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class WeatherDaoImplTest {

    private WeatherDaoImpl weatherDao;
    private WeatherEntity weatherEntity;
    private EntityManager em = mock(EntityManager.class);
    private Query query = mock(Query.class);
    //private Query query;

    @Before
    public void setValue() {
        weatherDao = new WeatherDaoImpl();
        weatherEntity = new WeatherEntity();
        weatherEntity.setVersion(0L);
        weatherEntity.setTemperature(1L);
        weatherEntity.setCity("Moscow");
        weatherDao.setEm(em);
        List<WeatherEntity> weatherEntityList = new ArrayList<>();
        weatherEntityList.add(weatherEntity);
        when(em.createQuery("SELECT we FROM WeatherEntity we ORDER BY we.id desc")).thenReturn(query);
        when(query.getResultList()).thenReturn(weatherEntityList);
    }

    @Test
    public void add() {
        weatherDao.add(weatherEntity);
        verify(em).persist(weatherEntity);
    }

    @Test
    public void getLastItem() {
        WeatherEntity weatherEntityGet = weatherDao.getLastItem();
        verify(query).getResultList();
        assertEquals(weatherEntityGet, weatherEntity);
    }

}
