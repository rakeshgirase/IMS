package com.exuberant.ims.dal;
import com.exuberant.ims.list.ListBrands;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Brands {
    public String id;
    public String brandName;
    public String brandComment;
    public String supplyrId;
    public String creatorId;
    public String date;
    public String supplyerName;
    public String creatorName;
    public ObservableList<ListBrands> brandDitails = FXCollections.observableArrayList();
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.dal\Brands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */