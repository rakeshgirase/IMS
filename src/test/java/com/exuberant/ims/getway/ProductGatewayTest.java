package com.exuberant.ims.getway;

import com.exuberant.ims.dal.Product;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductGatewayTest {

    private ProductGateway productGateway = new ProductGateway();

    @Test
    public void save() throws Exception {
        Product product = new Product("TEST DESC", new BigDecimal(10.0), new BigDecimal(100.0), new BigDecimal(120.0), new BigDecimal(2.0), new BigDecimal(80.0), "100 GMS");
        productGateway.save(product);
    }

}