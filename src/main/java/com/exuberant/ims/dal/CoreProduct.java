package com.exuberant.ims.dal;

import javax.persistence.*;

@Entity
@Table(name = "CORE_PRODUCT")
public class CoreProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CORE_PRODUCT_SEQ", allocationSize = 25)
    private Long id;
    private String description;
    private PackageType packageType;

    @ManyToOne(cascade = CascadeType.ALL)
    private Weight weight;

    public CoreProduct(String description, PackageType packageType, Weight weight) {
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

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
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
