package com.exuberant.ims.bll;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.getway.SupplyerGetway;
public class SupplyerBLL {
    SupplyerGetway supplyerGetway = new SupplyerGetway();
    public void save() {
    }
    public Object delete(Supplyer supplyer) {
        if (this.supplyerGetway.isNotUse(supplyer)) {
            this.supplyerGetway.delete(supplyer);
        }
        return supplyer;
    }
}