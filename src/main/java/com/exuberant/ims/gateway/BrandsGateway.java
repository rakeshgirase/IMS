package com.exuberant.ims.gateway;

import com.exuberant.ims.dal.Brand;
import com.exuberant.ims.persistence.HibernateRepository;

import java.util.List;

public class BrandsGateway {

    public void save(Brand brand) {
        HibernateRepository.getRepository().save(brand);
    }

    public void view(Brand brand) {
        List<Brand> brands = HibernateRepository.getRepository().getAll(Brand.class);
        brands.iterator().next();
    }

    public void selectedView(Brand brand) {
    }

    public void searchView(Brand brand) {
    }

    public void delete(Brand brand) {
        HibernateRepository.getRepository().delete(brand);
    }

    public void update(Brand brand) {
    }

    public boolean isNotUsed(Brand brand) {
        boolean inNotUse = false;
        return inNotUse;
    }
}
