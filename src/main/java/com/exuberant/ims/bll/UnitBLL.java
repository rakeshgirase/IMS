package com.exuberant.ims.bll;
import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.getway.UnitGetway;
public class UnitBLL {
    UnitGetway unitGetway = new UnitGetway();
    public Object save(Unit unit) {
        if (this.unitGetway.isUniqName(unit)) {
            this.unitGetway.save(unit);
        }
        return unit;
    }
    public Object delete(Unit unit) {
        if (this.unitGetway.isNotUse(unit)) {
            this.unitGetway.delete(unit);
        }
        return unit;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.bll\UnitBLL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */