package com.exuberant.ims.dal;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Party party;
    private OrderType orderType;
    @ElementCollection
    @Cascade(CascadeType.ALL)
    private Collection<ProductDetail> productDetails;
    private BigDecimal adjustment;
    private BigDecimal actualCost;
    private BigDecimal totalCost;

    public Order(OrderType orderType, Collection<ProductDetail> productDetails) {
        this.orderType = orderType;
        this.productDetails = productDetails;
    }

    public Order() {
    }

    private void calculateTotalCost() {
        totalCost = new BigDecimal(0);
        for (ProductDetail productDetail : productDetails) {
            totalCost.add(productDetail.getProduct().getMrp());
        }
    }

    private void calculateActualCost() {
        actualCost = new BigDecimal(0);
        for (ProductDetail productDetail : productDetails) {
            actualCost.add(productDetail.getProduct().getActualCost());
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
