package com.exuberant.ims.bll;

import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.gateway.CurrentProductGateway;

public class CurrentProductBLL {
    CurrentProductGateway currentProductGateway = new CurrentProductGateway();

    public void save(CurrentProduct currentProduct) {
        if (isUniqName(currentProduct)) {
            this.currentProductGateway.save(currentProduct);
        }
    }

    public void update(CurrentProduct currentProduct) {
        if ((isNotNull(currentProduct)) &&
                (isUpdate(currentProduct))) {
            if (checkUpdateCondition(currentProduct)) {
                this.currentProductGateway.update(currentProduct);
            } else if (isUniqName(currentProduct)) {
                this.currentProductGateway.update(currentProduct);
            }
        }
    }

    public boolean isUniqName(CurrentProduct currentProduct) {
        boolean isUniqname = true;
        return isUniqname;
    }

    public boolean isUpdate(CurrentProduct currentProduct) {
        boolean isUpdate = true;
        return isUpdate;
    }

    public boolean checkUpdateCondition(CurrentProduct currentProduct) {
        boolean isTrueUpdate = false;
        return isTrueUpdate;
    }

    public boolean isNotNull(CurrentProduct currentProduct) {
        boolean isNotNull = false;
        return isNotNull;
    }

    public Object delete(CurrentProduct currentProduct) {
        if (this.currentProductGateway.isSold(currentProduct)) {
            this.currentProductGateway.delete(currentProduct);
        }
        return currentProduct;
    }
}