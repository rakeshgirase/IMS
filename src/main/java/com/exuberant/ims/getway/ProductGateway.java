package com.exuberant.ims.getway;

import com.exuberant.ims.dal.Product;
import com.exuberant.ims.gateway.HibernateRepository;

public class ProductGateway {

    public void save(Product newProduct){
        HibernateRepository.getRepository().save(newProduct);
    }

}
