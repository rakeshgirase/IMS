package com.exuberant.ims.dal;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {

    private static final java.math.BigDecimal HUNDRED = new BigDecimal(100);
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PRODUCT_SEQ", allocationSize = 25)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private CoreProduct coreProduct;
    private BigDecimal costOfSelling;
    private BigDecimal mrp;
    private BigDecimal discount;
    private BigDecimal actualCost;
    private BigDecimal quantity;
    @Transient
    private boolean calculateActualCost = true;

    public Product(String description, BigDecimal quantity, BigDecimal costOfSelling, BigDecimal mrp, BigDecimal discount, BigDecimal actualCost, String weight) {
        this.coreProduct = new CoreProduct(description, PackageType.PACK, new Weight(new BigDecimal(weight), Unit.GRAMS));
        this.quantity = quantity;
        this.costOfSelling = costOfSelling;
        this.mrp = mrp;
        this.discount = discount;
        this.actualCost = actualCost;
    }

    public Product() {
    }

    public void setCalculateActualCost(Boolean calculateActualCost) {
        this.calculateActualCost = calculateActualCost;
    }

    public CoreProduct getCoreProduct() {
        return coreProduct;
    }

    public void setCoreProduct(CoreProduct coreProduct) {
        this.coreProduct = coreProduct;
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
        return coreProduct.getWeight();
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

    public String getTotalWeight() {
        return "TOTAL WEIGHT";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return coreProduct != null ? coreProduct.equals(product.coreProduct) : product.coreProduct == null;
    }

    @Override
    public int hashCode() {
        return coreProduct != null ? coreProduct.hashCode() : 0;
    }
}

