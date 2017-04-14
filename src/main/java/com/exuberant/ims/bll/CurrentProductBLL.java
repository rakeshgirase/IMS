package com.exuberant.ims.bll;

import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.getway.CurrentProductGetway;

public class CurrentProductBLL {
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();

    public void save(CurrentProduct currentProduct) {
        if (isUniqName(currentProduct)) {
            this.currentProductGetway.save(currentProduct);
        }
    }

    public void update(CurrentProduct currentProduct) {
        if ((isNotNull(currentProduct)) &&
                (isUpdate(currentProduct))) {
            if (checkUpdateCondition(currentProduct)) {
                this.currentProductGetway.update(currentProduct);
            } else if (isUniqName(currentProduct)) {
                this.currentProductGetway.update(currentProduct);
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
        if (this.currentProductGetway.isSold(currentProduct)) {
            this.currentProductGetway.delete(currentProduct);
        }
        return currentProduct;
    }
}