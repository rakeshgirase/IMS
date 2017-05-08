package com.exuberant.ims.gateway;


import com.exuberant.ims.dal.Order;
import org.junit.Test;

public class OrderGatewayTest {

    private OrderGateway orderGateway = new OrderGateway();

    @Test
    public void saveOrder() throws Exception {
        Order order = new Order();
        orderGateway.save(order);
    }
}