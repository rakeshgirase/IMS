package com.exuberant.ims.getway;

import com.exuberant.ims.dal.Supplier;
import com.exuberant.ims.gateway.HibernateRepository;

public class SupplyerGetway {

    public void save(Supplier supplier) {
        HibernateRepository.getRepository().save(supplier);
    }

    public void view(Supplier supplier) {

    }

    public void searchView(Supplier supplier) {

    }

    public void selectedView(Supplier supplier) {

    }

    public void update(Supplier supplier) {
        System.out.println("we are in update");
    }

    public void delete(Supplier supplier) {
        HibernateRepository.getRepository().delete(supplier);
    }

    public boolean isUniqSupplyerName(Supplier supplier) {
        boolean uniqSupplyer = false;
        return uniqSupplyer;
    }

    public void updateNow(Supplier supplier) {

    }

    private boolean isUpdate(Supplier supplier) {
        boolean isUpdate = false;
        return isUpdate;
    }

    public boolean isNotUse(Supplier supplier) {
        boolean isNotUse = false;
        return isNotUse;
    }
}
