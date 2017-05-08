package com.exuberant.ims.gateway;

import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.persistence.HibernateRepository;

import java.util.List;

public class CurrentProductGateway {
    public void save(CurrentProduct currentProduct) {
        HibernateRepository.getRepository().save(currentProduct);
    }

    public void view(CurrentProduct currentProduct) {
        List<CurrentProduct> currentProducts = HibernateRepository.getRepository().getAll(CurrentProduct.class);
    }

    public void selectedView(CurrentProduct currentProduct) {
        HibernateRepository.getRepository().getAll(CurrentProduct.class);
    }

    public void viewFistTen(CurrentProduct currentProduct) {
        HibernateRepository.getRepository().getAll(CurrentProduct.class);
    }

    public void searchView(CurrentProduct currentProduct) {
    }

    public void searchBySupplyer(CurrentProduct currentProduct) {
    }

    public void searchByBrand(CurrentProduct currentProduct) {
    }

    public void searchByCatagory(CurrentProduct currentProduct) {
    }

    public void searchByRMA(CurrentProduct currentProduct) {

    }

    public void sView(CurrentProduct currentProduct) {
        currentProduct = HibernateRepository.getRepository().get(CurrentProduct.class, currentProduct.getId());
    }

    public void cbSupplyer(CurrentProduct currentProduct) {

    }

    public void update(CurrentProduct currentProduct) {
        HibernateRepository.getRepository().update(currentProduct);
    }

    public void delete(CurrentProduct currentProduct) {
        HibernateRepository.getRepository().delete(currentProduct);
    }

    public boolean isSold(CurrentProduct currentProduct) {
        boolean isSold = false;
        return isSold;
    }
}
