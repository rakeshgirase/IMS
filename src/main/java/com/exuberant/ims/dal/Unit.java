package com.exuberant.ims.dal;
import com.exuberant.ims.list.ListUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.Entity;

@Entity
public class Unit {
    public String id;
    public String unitName;
    public String unitDescription;
    public Long creatorId;
    public Users users;
    public String date;
    public ObservableList<ListUnit> unitDetails = FXCollections.observableArrayList();
}
