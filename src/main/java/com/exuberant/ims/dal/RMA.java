package com.exuberant.ims.dal;

import com.exuberant.ims.list.ListRma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.Entity;

@Entity
public class RMA {
    public String id;
    public String rmaName;
    public String rmaDays;
    public String rmaComment;
    public Long creatorId;
    public String creatorName;
    public String date;
    public ObservableList<ListRma> rmaDetails = FXCollections.observableArrayList();
}
