package com.exuberant.ims.gateway;

import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.persistence.HibernateRepository;
import com.exuberant.ims.util.PropertyService;

public class UnitGateway {

    String db = PropertyService.getInstance().getProperty("db");

    public void save(Unit unit) {
        HibernateRepository.getRepository().save(unit);
    }

    public void view(Unit unit) {

    }

    public void selectedView(Unit unit) {
    }

    public void searchView(Unit unit) {

    }

    public void update(Unit unit) {
        HibernateRepository.getRepository().update(unit);
    }

    public void delete(Unit unit) {
        HibernateRepository.getRepository().delete(unit);
    }
}
