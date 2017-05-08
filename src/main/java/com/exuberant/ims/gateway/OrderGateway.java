package com.exuberant.ims.gateway;

import com.exuberant.ims.dal.Order;
import com.exuberant.ims.persistence.HibernateRepository;

public class OrderGateway {

    public void save(Order order) {
        HibernateRepository.getRepository().save(order);
    }
}
