package com.exuberant.ims.getway;

import com.exuberant.ims.dal.Product;
import com.exuberant.ims.gateway.HibernateRepository;

import java.util.List;

public class ProductGateway {

    public void save(Product newProduct){
        HibernateRepository.getRepository().save(newProduct);
    }

    public List<Product> getAll() {
        return HibernateRepository.getRepository().getAll(Product.class);
    }
}
