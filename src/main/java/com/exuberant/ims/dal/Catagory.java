package com.exuberant.ims.dal;
import com.exuberant.ims.list.ListCatagory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Catagory {
    public String id;
    public String catagoryName;
    public String catagoryDescription;
    public String brandId;
    public String date;
    public String creatorId;
    public String creatorName;
    public String brandName;
    public String supplyerId;
    public String supplyerName;
    public ObservableList<ListCatagory> catagoryDetails = FXCollections.observableArrayList();
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.dal\Catagory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */