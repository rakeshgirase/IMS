package com.exuberant.ims.dal;
import com.exuberant.ims.list.ListPreSell;
import com.exuberant.ims.list.ListSold;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class SellCart {
    public String Id;
    public String sellID;
    public String customerID;
    public String productID;
    public String pursesPrice;
    public String sellPrice;
    public String quantity;
    public String totalPrice;
    public String pursrsDate;
    public String warrentyVoidDate;
    public String sellerID;
    public String sellDate;
    public String oldQuentity;
    public String customerName;
    public String sellerName;
    public String givenProductID;
    public ObservableList<ListPreSell> carts = FXCollections.observableArrayList();
    public ObservableList<ListSold> soldList = FXCollections.observableArrayList();
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.dal\SellCart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */