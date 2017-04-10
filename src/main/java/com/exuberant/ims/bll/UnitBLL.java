package com.exuberant.ims.bll;
import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.getway.UnitGetway;
public class UnitBLL {
    UnitGetway unitGetway = new UnitGetway();
    public Object save(Unit unit) {
        this.unitGetway.save(unit);
        return unit;
    }
    public Object delete(Unit unit) {
        this.unitGetway.delete(unit);
        return unit;
    }
}