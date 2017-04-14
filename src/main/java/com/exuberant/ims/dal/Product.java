package com.exuberant.ims.dal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product {

    private static final java.math.BigDecimal HUNDRED = new BigDecimal(100);
    @EmbeddedId
    private ProductId id;
    private Long quantity;
    private BigDecimal costOfSelling;
    private BigDecimal mrp;
    private BigDecimal discount;
    private BigDecimal actualCost;
    private Boolean calculateActualCost;
    private Weight weight;

    public ProductId getId() {
        return id;
    }

    public void setId(ProductId id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCostOfSelling() {
        return costOfSelling;
    }

    public void setCostOfSelling(BigDecimal costOfSelling) {
        this.costOfSelling = costOfSelling;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public void setMrp(BigDecimal mrp) {
        this.mrp = mrp;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getActualCost() {
        if (calculateActualCost) {
            actualCost = mrp.subtract(mrp.multiply(discount.divide(HUNDRED)));
        }
        return actualCost;
    }

    public void setActualCost(BigDecimal actualCost) {
        this.actualCost = actualCost;
        setCalculateActualCost(false);
    }

    public void setCalculateActualCost(Boolean calculateActualCost) {
        this.calculateActualCost = calculateActualCost;
    }
}

class ProductId {
    private String name;
    private PackageType packageType;
}