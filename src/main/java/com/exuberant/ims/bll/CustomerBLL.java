package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Customer;
import com.exuberant.ims.gateway.CustomerGateway;
import com.exuberant.ims.util.PropertyService;

public class CustomerBLL {
    CustomerGateway customerGateway = new CustomerGateway();

    String db = PropertyService.getInstance().getProperty("db");

    public void save(Customer customer) {
        if (isUniqName(customer)) {
            this.customerGateway.save(customer);
        }
    }

    public void update(Customer customer) {
        if (isUpdate(customer)) {
            if (isSame(customer)) {
                this.customerGateway.update(customer);
            } else if (isUniqName(customer)) {
                this.customerGateway.update(customer);
            }
        }
    }

    public boolean isUniqName(Customer customer) {
        boolean inUniqName = false;
        return inUniqName;
    }

    public boolean isUpdate(Customer customer) {
        boolean isUpdate = false;
        return isUpdate;
    }

    private boolean isSame(Customer customer) {
        boolean isSame = false;
        return isSame;
    }
}