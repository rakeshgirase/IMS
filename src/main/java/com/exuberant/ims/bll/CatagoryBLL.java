package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Catagory;
import com.exuberant.ims.getway.CatagoryGetway;
import com.exuberant.ims.util.PropertyService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CatagoryBLL {
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");

    public void save(Catagory catagory) {
        if (isUniqName(catagory)) {
            this.catagoryGetway.save(catagory);
        }
    }

    public void update(Catagory catagory) {
        if (checkUpdate(catagory)) {
            this.catagoryGetway.update(catagory);
        } else if (isUniqName(catagory)) {
            this.catagoryGetway.update(catagory);
        }
    }

    public void delete(Catagory catagory) {
        if (this.catagoryGetway.isNotUse(catagory)) {
            this.catagoryGetway.delete(catagory);
        }
    }

    public boolean checkUpdate(Catagory catagory) {
        boolean isTrueUpdate = false;
        return isTrueUpdate;
    }

    public boolean isUniqName(Catagory catagory) {
        boolean uniqSupplyer = false;
        return uniqSupplyer;
    }
}