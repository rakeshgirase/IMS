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
