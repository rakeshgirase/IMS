package com.exuberant.ims.dal;
import com.exuberant.ims.list.ListUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Unit {
    public String id;
    public String unitName;
    public String unitDescription;
    public String creatorId;
    public String creatorName;
    public String date;
    public ObservableList<ListUnit> unitDetails = FXCollections.observableArrayList();
}