package com.exuberant.ims.getway;

import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.gateway.HibernateRepository;
import com.exuberant.ims.util.PropertyService;

public class UnitGetway {

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
