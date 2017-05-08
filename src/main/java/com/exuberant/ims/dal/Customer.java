package com.exuberant.ims.dal;

import com.exuberant.ims.list.CustomerGui;

import javax.persistence.Entity;

@Entity
public class Customer {
    public String id;
    public String customerName;
    public String customerConNo;
    public String customerAddress;
    public String totalBuy;
    public String date;
    public Long userId;
    public String userName;

    public CustomerGui toGui(){
        return new CustomerGui(id,customerName, customerConNo,customerAddress, totalBuy, userName, date);
    }
}
