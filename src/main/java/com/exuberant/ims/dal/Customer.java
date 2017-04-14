package com.exuberant.ims.dal;

import com.exuberant.ims.list.ListCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public ObservableList<ListCustomer> customerList = FXCollections.observableArrayList();
}
