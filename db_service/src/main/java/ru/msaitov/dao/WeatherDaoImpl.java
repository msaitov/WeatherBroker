package ru.msaitov.dao;

import ru.msaitov.model.WeatherEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class WeatherDaoImpl implements WeatherDao {


    private EntityManager em;

    @PersistenceContext(unitName="WeatherPersistenceUnit")
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(WeatherEntity weatherEntity) {
        em.persist(weatherEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherEntity getLastItem() {
        Query query = em.createQuery("SELECT we FROM WeatherEntity we ORDER BY we.id desc");
        List<WeatherEntity> weatherEntityList = query.getResultList();
        return weatherEntityList.get(0);
    }
}
