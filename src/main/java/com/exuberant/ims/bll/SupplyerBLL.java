package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Supplier;
import com.exuberant.ims.getway.SupplyerGetway;

public class SupplyerBLL {
    SupplyerGetway supplyerGetway = new SupplyerGetway();

    public void save() {
    }

    public Object delete(Supplier supplier) {
        if (this.supplyerGetway.isNotUse(supplier)) {
            this.supplyerGetway.delete(supplier);
        }
        return supplier;
    }
}