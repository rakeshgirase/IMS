package com.exuberant.ims.dal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product {

    private static final java.math.BigDecimal HUNDRED = new BigDecimal(100);
    @EmbeddedId
    private ProductId id;
    private BigDecimal quantity;
    private BigDecimal costOfSelling;
    private BigDecimal mrp;
    private BigDecimal discount;
    private BigDecimal actualCost;
    private Boolean calculateActualCost;


    public void setCalculateActualCost(Boolean calculateActualCost) {
        this.calculateActualCost = calculateActualCost;
    }

    public ProductId getId() {
        return id;
    }

    public void setId(ProductId id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
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

    public Weight getWeight() {
        return id.getWeight();
    }

    public BigDecimal getTotalCostOfSelling() {
        return costOfSelling.multiply(quantity);
    }

    public BigDecimal getTotalMrp() {
        return mrp.multiply(quantity);
    }

    public BigDecimal getTotalActualCost() {
        return actualCost.multiply(quantity);
    }

    public Weight getTotalWeight() {
        return id.getWeight().multiply(quantity);
    }
}

class ProductId {
    private String name;
    private PackageType packageType;
    private Weight weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }
}