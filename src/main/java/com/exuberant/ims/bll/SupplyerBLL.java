package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Supplier;
import com.exuberant.ims.gateway.SupplyerGateway;

public class SupplyerBLL {
    SupplyerGateway supplyerGateway = new SupplyerGateway();

    public void save() {
    }

    public Object delete(Supplier supplier) {
        if (this.supplyerGateway.isNotUse(supplier)) {
            this.supplyerGateway.delete(supplier);
        }
        return supplier;
    }
}