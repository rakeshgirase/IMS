package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Brand;
import com.exuberant.ims.getway.BrandsGetway;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class BrandBLL {

    BrandsGetway brandsGetway = new BrandsGetway();

    public void save(Brand brand) {
        if (isUniqName(brand)) {
            this.brandsGetway.save(brand);
        }
    }

    public void update(Brand brand) {
        if (isTrueUpdate(brand)) {
            this.brandsGetway.update(brand);
        } else if (isUniqName(brand)) {
            this.brandsGetway.update(brand);
        }
    }

    public void delete(Brand brand) {
        if (this.brandsGetway.isNotUsed(brand)) {
            this.brandsGetway.delete(brand);
        }
    }

    public boolean isTrueUpdate(Brand brand) {
        boolean isTrueUpdate = true;
        return isTrueUpdate;
    }

    public boolean isUniqName(Brand brand) {
        boolean uniqSupplyer = false;
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucess");
        alert.setHeaderText("ERROR : used");
        alert.setContentText("Brand  '" + brand.brandName + "' Already exist");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
        return uniqSupplyer;
    }
}