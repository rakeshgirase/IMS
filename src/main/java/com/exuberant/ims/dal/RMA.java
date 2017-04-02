package com.exuberant.ims.dal;
import com.exuberant.ims.list.ListRma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class RMA {
    public String id;
    public String rmaName;
    public String rmaDays;
    public String rmaComment;
    public String creatorId;
    public String creatorName;
    public String date;
    public ObservableList<ListRma> rmaDetails = FXCollections.observableArrayList();
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.dal\RMA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */