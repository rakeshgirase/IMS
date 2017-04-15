package com.exuberant.ims.dal;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CurrentStock extends Stock{

    public BigDecimal findTotalCost(){
        return findCost(product -> product.getActualCost());
    }

}
