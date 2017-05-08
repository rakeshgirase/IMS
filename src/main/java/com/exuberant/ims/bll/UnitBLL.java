package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.gateway.UnitGateway;

public class UnitBLL {
    UnitGateway unitGateway = new UnitGateway();

    public Object save(Unit unit) {
        this.unitGateway.save(unit);
        return unit;
    }

    public Object delete(Unit unit) {
        this.unitGateway.delete(unit);
        return unit;
    }
}