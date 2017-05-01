package com.exuberant.ims.dal;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class ProductGui {

    private static final BigDecimal HUNDRED = new BigDecimal(100);
    private SimpleStringProperty description;
    private SimpleStringProperty  quantity;
    private SimpleStringProperty  costOfSelling;
    private SimpleStringProperty  mrp;
    private SimpleStringProperty  discount;
    private SimpleStringProperty  actualCost;

    public ProductGui(Product product) {
        this.description = new SimpleStringProperty(product.getId().getDescription());
        this.quantity = new SimpleStringProperty(product.getQuantity().toString());
        this.costOfSelling = new SimpleStringProperty(product.getCostOfSelling().toString());
        this.mrp = new SimpleStringProperty(product.getMrp().toString());
        this.discount = new SimpleStringProperty(product.getDiscount().toString());
        this.actualCost = new SimpleStringProperty(product.getActualCost().toString());
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getCostOfSelling() {
        return costOfSelling.get();
    }

    public SimpleStringProperty costOfSellingProperty() {
        return costOfSelling;
    }

    public void setCostOfSelling(String costOfSelling) {
        this.costOfSelling.set(costOfSelling);
    }

    public String getMrp() {
        return mrp.get();
    }

    public SimpleStringProperty mrpProperty() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp.set(mrp);
    }

    public String getDiscount() {
        return discount.get();
    }

    public SimpleStringProperty discountProperty() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount.set(discount);
    }

    public String getActualCost() {
        return actualCost.get();
    }

    public SimpleStringProperty actualCostProperty() {
        return actualCost;
    }

    public void setActualCost(String actualCost) {
        this.actualCost.set(actualCost);
    }
}

