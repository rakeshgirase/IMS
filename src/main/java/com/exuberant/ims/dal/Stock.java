package com.exuberant.ims.dal;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

@MappedSuperclass
public class Stock {

    @Id
    private Long id;
    @OneToMany
    private Collection<Product> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.add(product);
    }

    public BigDecimal findCost(Function<Product, BigDecimal> function){
        BigDecimal total = new BigDecimal(0);
        for (Product product: products){
            total.add(function.apply(product));
        }
        return  total;
    }
}
