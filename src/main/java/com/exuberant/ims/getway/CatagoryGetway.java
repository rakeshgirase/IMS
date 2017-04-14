package com.exuberant.ims.getway;

import com.exuberant.ims.dal.Catagory;
import com.exuberant.ims.gateway.HibernateRepository;
import com.exuberant.ims.util.PropertyService;

public class CatagoryGetway {

    String db = PropertyService.getInstance().getProperty("db");

    public void save(Catagory catagory) {
        HibernateRepository.getRepository().save(catagory);
    }

    public void view(Catagory catagory) {

    }

    public void selectedView(Catagory catagory) {
    }

    public void brandView(Catagory catagory) {
    }

    public void searchView(Catagory catagory) {

    }

    public void update(Catagory catagory) {

    }

    public void delete(Catagory catagory) {

    }

    public boolean isNotUse(Catagory catagory) {
        boolean isNotUse = true;
        return isNotUse;
    }
}
