package com.exuberant.ims.dal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    private Long id;
    private Product product;
    private Long quantity;

}
