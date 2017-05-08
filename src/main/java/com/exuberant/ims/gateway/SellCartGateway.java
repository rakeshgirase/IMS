package com.exuberant.ims.gateway;

import com.exuberant.ims.dal.SellCart;
import com.exuberant.ims.persistence.HibernateRepository;

public class SellCartGateway {


    public void save(SellCart sellCart) {
        HibernateRepository.getRepository().save(sellCart);
    }

    public void view(SellCart sellCart) {
    }

    public void firstTenView(SellCart sellCart) {
    }

    public void searchView(SellCart sellCart) {
    }
}
