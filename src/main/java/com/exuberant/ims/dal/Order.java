package com.exuberant.ims.dal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Order {
    @Id
    private Long id;
    private Party party;
    @OneToMany
    private Collection<Product> products;
    private BigDecimal adjustment;
    private BigDecimal actualCost;
    private BigDecimal totalCost;

    private void calculateTotalCost() {
        totalCost = new BigDecimal(0);
        for (Product product : products) {
            totalCost.add(product.getMrp());
        }
    }

    private void calculateActualCost() {
        actualCost = new BigDecimal(0);
        for (Product product : products) {
            actualCost.add(product.getActualCost());
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public BigDecimal getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(BigDecimal adjustment) {
        this.adjustment = adjustment;
    }

    public BigDecimal getActualCost() {
        return actualCost;
    }

    public void setActualCost(BigDecimal actualCost) {
        this.actualCost = actualCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
