package com.exuberant.ims.gateway;


import com.exuberant.ims.dal.User;
import org.junit.Test;

public class UserGatewayTest {

    private UserGateway userGateway = new UserGateway();

    @Test
    public void save() throws Exception {
        User user = new User("rakeshgirase", "Rakesh Girase", "Pass");
        userGateway.save(user);
    }
}