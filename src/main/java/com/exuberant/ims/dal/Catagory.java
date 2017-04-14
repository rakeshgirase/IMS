package com.exuberant.ims.dal;

import com.exuberant.ims.list.ListCatagory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.Entity;

@Entity
public class Catagory {
    public String id;
    public String catagoryName;
    public String catagoryDescription;
    public String brandId;
    public String date;
    public Users users;
    public String creatorName;
    public String brandName;
    public String supplyerId;
    public String supplyerName;
    public ObservableList<ListCatagory> catagoryDetails = FXCollections.observableArrayList();
}
