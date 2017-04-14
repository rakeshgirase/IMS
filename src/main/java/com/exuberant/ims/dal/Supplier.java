package com.exuberant.ims.dal;

import com.exuberant.ims.list.ListSupplyer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.Entity;

@Entity
public class Supplier {

    public String id;
    public String supplierName;
    public String supplierContactNumber;
    public String supplierAddress;
    public String supplierDescription;
    public Long creatorId;
    public String date;
    public ObservableList<ListSupplyer> supplierDetails = FXCollections.observableArrayList();
}
