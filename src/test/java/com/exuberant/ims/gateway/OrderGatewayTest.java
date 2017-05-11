package com.exuberant.ims.gateway;


import com.exuberant.ims.dal.Order;
import com.exuberant.ims.dal.OrderType;
import com.exuberant.ims.dal.Product;
import com.exuberant.ims.dal.ProductDetail;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

public class OrderGatewayTest {

    private OrderGateway orderGateway = new OrderGateway();

    @Test
    public void saveOrder() throws Exception {
        Product p1 = new Product("TEST DESC 1", new BigDecimal(10.0), new BigDecimal(100.0), new BigDecimal(120.0), new BigDecimal(2.0), new BigDecimal(80.0), "100");;
        Product p2 = new Product("TEST DESC 2 ", new BigDecimal(100.0), new BigDecimal(100.0), new BigDecimal(130.0), new BigDecimal(2.0), new BigDecimal(100.0), "200");;
        /*ProductGateway productGateway = new ProductGateway();
        productGateway.getAll().forEach(System.out::println);
        productGateway.save(p1);
        productGateway.save(p2);*/
        ProductDetail pd1 = new ProductDetail(p1, p1.getMrp());
        ProductDetail pd2 = new ProductDetail(p2, p2.getMrp());
        Collection<ProductDetail> productDetails = Arrays.asList(pd1, pd2);
        Order order = new Order(OrderType.SELL, productDetails);
        orderGateway.save(order);
    }
}