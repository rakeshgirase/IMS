package com.exuberant.ims.dal;
import com.exuberant.ims.list.ListSupplyer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Supplyer {
    public String id;
    public String supplyerName;
    public String supplyerContactNumber;
    public String supplyerAddress;
    public String supplyerDescription;
    public String creatorId;
    public String date;
    public ObservableList<ListSupplyer> supplyerDetails = FXCollections.observableArrayList();
}
