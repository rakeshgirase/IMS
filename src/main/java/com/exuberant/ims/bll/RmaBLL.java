package com.exuberant.ims.bll;

import com.exuberant.ims.dal.RMA;
import com.exuberant.ims.getway.RmaGetway;
import com.exuberant.ims.util.PropertyService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RmaBLL {
    RmaGetway rmaGetway = new RmaGetway();
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");

    public void save(RMA rma) {
        if (isUniqName(rma)) {
            this.rmaGetway.save(rma);
        }
    }

    public void update(RMA rma) {
        if (sameName(rma)) {
            this.rmaGetway.update(rma);
        } else if (isUniqName(rma)) {
            this.rmaGetway.update(rma);
        }
    }

    public Object delete(RMA rma) {
        if (this.rmaGetway.isNotUse(rma)) {
            this.rmaGetway.delete(rma);
        }
        return rma;
    }

    public boolean sameName(RMA rma) {
        boolean sameName = false;
        return sameName;
    }

    public boolean isUniqName(RMA rma) {
        boolean uniqRMA = true;
        return uniqRMA;
    }
}
