package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Customer;
import com.exuberant.ims.getway.CustomerGetway;
import com.exuberant.ims.util.PropertyService;

public class CustomerBLL {
    CustomerGetway customerGetway = new CustomerGetway();

    String db = PropertyService.getInstance().getProperty("db");

    public void save(Customer customer) {
        if (isUniqName(customer)) {
            this.customerGetway.save(customer);
        }
    }

    public void update(Customer customer) {
        if (isUpdate(customer)) {
            if (isSame(customer)) {
                this.customerGetway.update(customer);
            } else if (isUniqName(customer)) {
                this.customerGetway.update(customer);
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