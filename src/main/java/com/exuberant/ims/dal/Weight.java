package com.exuberant.ims.dal;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Weight {

    @Id
    private Long id;
    private BigDecimal weight;
    private Unit unit = Unit.GRAMS;

    public Weight(BigDecimal weight, Unit unit) {
        this.weight = weight;
        this.unit = unit;
    }

    public Weight(BigDecimal weight) {
        this.weight = weight;
    }

    public Weight multiply(BigDecimal quantity) {
        return new Weight(weight.multiply(quantity), this.unit);
    }
}
