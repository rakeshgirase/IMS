package com.exuberant.ims.dal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CORE_PRODUCT")
public class CoreProduct {
    @Id
    @GeneratedValue()
    private Long id;
    private String description;
    private PackageType packageType;

    //FIXME: Change this to class
    private String weight;

    public CoreProduct(String description, PackageType packageType, String weight) {
        this.description = description;
        this.packageType = packageType;
        this.weight = weight;
    }

    public CoreProduct() {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CoreProduct{" +
                "description='" + description + '\'' +
                ", packageType=" + packageType +
                ", weight=" + weight +
                '}';
    }
}
