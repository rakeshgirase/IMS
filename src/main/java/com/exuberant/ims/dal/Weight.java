package com.exuberant.ims.dal;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "WEIGHT_SEQ", allocationSize = 25)
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

    public Weight() {
    }

    public Weight multiply(BigDecimal quantity) {
        return new Weight(weight.multiply(quantity), this.unit);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "id=" + id +
                ", weight=" + weight +
                ", unit=" + unit +
                '}';
    }
}
