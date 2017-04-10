package com.exuberant.ims.getway;
import com.exuberant.ims.dal.SellCart;
import com.exuberant.ims.gateway.HibernateRepository;
public class SellCartGerway {


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
