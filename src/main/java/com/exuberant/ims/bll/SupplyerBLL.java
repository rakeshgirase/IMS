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
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.bll\SupplyerBLL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */