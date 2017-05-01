package com.exuberant.ims.dal;

import javax.persistence.Transient;
import java.io.Serializable;

public class ProductId implements Serializable{
    private String description;
    private PackageType packageType;
    @Transient
    private Weight weight;

    public ProductId(String description, PackageType packageType, Weight weight) {
        this.description = description;
        this.packageType = packageType;
        this.weight = weight;
    }

    public ProductId() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
