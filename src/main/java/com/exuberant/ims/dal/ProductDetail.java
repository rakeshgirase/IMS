package com.exuberant.ims.dal;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "PRODUCT_DETAILS")
public class ProductDetail {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Product product;
    private BigDecimal adjustment;
    private BigDecimal sellingCost;

    public ProductDetail(Product product, BigDecimal sellingCost) {
        this.product = product;
        this.sellingCost = sellingCost;
    }

    public ProductDetail() {
    }

    public Product getProduct() {
        return product;
    }
}
