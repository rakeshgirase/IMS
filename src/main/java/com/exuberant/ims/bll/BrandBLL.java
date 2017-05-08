package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Brand;
import com.exuberant.ims.gateway.BrandsGateway;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class BrandBLL {

    BrandsGateway brandsGateway = new BrandsGateway();

    public void save(Brand brand) {
        if (isUniqName(brand)) {
            this.brandsGateway.save(brand);
        }
    }

    public void update(Brand brand) {
        if (isTrueUpdate(brand)) {
            this.brandsGateway.update(brand);
        } else if (isUniqName(brand)) {
            this.brandsGateway.update(brand);
        }
    }

    public void delete(Brand brand) {
        if (this.brandsGateway.isNotUsed(brand)) {
            this.brandsGateway.delete(brand);
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