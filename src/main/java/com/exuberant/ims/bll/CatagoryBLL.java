package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Catagory;
import com.exuberant.ims.gateway.CatagoryGateway;
import com.exuberant.ims.util.PropertyService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CatagoryBLL {
    CatagoryGateway catagoryGateway = new CatagoryGateway();
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");

    public void save(Catagory catagory) {
        if (isUniqName(catagory)) {
            this.catagoryGateway.save(catagory);
        }
    }

    public void update(Catagory catagory) {
        if (checkUpdate(catagory)) {
            this.catagoryGateway.update(catagory);
        } else if (isUniqName(catagory)) {
            this.catagoryGateway.update(catagory);
        }
    }

    public void delete(Catagory catagory) {
        if (this.catagoryGateway.isNotUse(catagory)) {
            this.catagoryGateway.delete(catagory);
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