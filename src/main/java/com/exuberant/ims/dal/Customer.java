package com.exuberant.ims.dal;
import com.exuberant.ims.list.ListCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Customer {
    public String id;
    public String customerName;
    public String customerConNo;
    public String customerAddress;
    public String totalBuy;
    public String date;
    public String userId;
    public String userName;
    public ObservableList<ListCustomer> customerList = FXCollections.observableArrayList();
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.dal\Customer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */