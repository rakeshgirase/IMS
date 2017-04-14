package com.exuberant.ims.dal;

import javax.persistence.Entity;

@Entity
public class CurrentProduct {
    public String id;
    public String productId;
    public String productName;
    public String quantity;
    public String description;
    public String supplierId;
    public String brandId;
    public String catagoryId;
    public String unitId;
    public String pursesPrice;
    public String sellPrice;
    public String rmaId;
    public Long userId;
    public String date;
    public String warrentyVoidDate;
    public String supplierName;
    public String brandName;
    public String catagoryName;
    public String unitName;
    public String rmaName;
    public String userName;
    public String rmaDayesss;
    public String rmaDayes;
    public String supplyerList;
    //private ObservableList<ListProduct> currentProductList = FXCollections.observableArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getPursesPrice() {
        return pursesPrice;
    }

    public void setPursesPrice(String pursesPrice) {
        this.pursesPrice = pursesPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getRmaId() {
        return rmaId;
    }

    public void setRmaId(String rmaId) {
        this.rmaId = rmaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWarrentyVoidDate() {
        return warrentyVoidDate;
    }

    public void setWarrentyVoidDate(String warrentyVoidDate) {
        this.warrentyVoidDate = warrentyVoidDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRmaName() {
        return rmaName;
    }

    public void setRmaName(String rmaName) {
        this.rmaName = rmaName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRmaDayesss() {
        return rmaDayesss;
    }

    public void setRmaDayesss(String rmaDayesss) {
        this.rmaDayesss = rmaDayesss;
    }

    public String getRmaDayes() {
        return rmaDayes;
    }

    public void setRmaDayes(String rmaDayes) {
        this.rmaDayes = rmaDayes;
    }

    public String getSupplyerList() {
        return supplyerList;
    }

    public void setSupplyerList(String supplyerList) {
        this.supplyerList = supplyerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentProduct that = (CurrentProduct) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (supplierId != null ? !supplierId.equals(that.supplierId) : that.supplierId != null) return false;
        if (brandId != null ? !brandId.equals(that.brandId) : that.brandId != null) return false;
        if (catagoryId != null ? !catagoryId.equals(that.catagoryId) : that.catagoryId != null) return false;
        if (unitId != null ? !unitId.equals(that.unitId) : that.unitId != null) return false;
        if (pursesPrice != null ? !pursesPrice.equals(that.pursesPrice) : that.pursesPrice != null) return false;
        if (sellPrice != null ? !sellPrice.equals(that.sellPrice) : that.sellPrice != null) return false;
        if (rmaId != null ? !rmaId.equals(that.rmaId) : that.rmaId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (warrentyVoidDate != null ? !warrentyVoidDate.equals(that.warrentyVoidDate) : that.warrentyVoidDate != null)
            return false;
        if (supplierName != null ? !supplierName.equals(that.supplierName) : that.supplierName != null) return false;
        if (brandName != null ? !brandName.equals(that.brandName) : that.brandName != null) return false;
        if (catagoryName != null ? !catagoryName.equals(that.catagoryName) : that.catagoryName != null) return false;
        if (unitName != null ? !unitName.equals(that.unitName) : that.unitName != null) return false;
        if (rmaName != null ? !rmaName.equals(that.rmaName) : that.rmaName != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (rmaDayesss != null ? !rmaDayesss.equals(that.rmaDayesss) : that.rmaDayesss != null) return false;
        if (rmaDayes != null ? !rmaDayes.equals(that.rmaDayes) : that.rmaDayes != null) return false;
        return supplyerList != null ? supplyerList.equals(that.supplyerList) : that.supplyerList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (supplierId != null ? supplierId.hashCode() : 0);
        result = 31 * result + (brandId != null ? brandId.hashCode() : 0);
        result = 31 * result + (catagoryId != null ? catagoryId.hashCode() : 0);
        result = 31 * result + (unitId != null ? unitId.hashCode() : 0);
        result = 31 * result + (pursesPrice != null ? pursesPrice.hashCode() : 0);
        result = 31 * result + (sellPrice != null ? sellPrice.hashCode() : 0);
        result = 31 * result + (rmaId != null ? rmaId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (warrentyVoidDate != null ? warrentyVoidDate.hashCode() : 0);
        result = 31 * result + (supplierName != null ? supplierName.hashCode() : 0);
        result = 31 * result + (brandName != null ? brandName.hashCode() : 0);
        result = 31 * result + (catagoryName != null ? catagoryName.hashCode() : 0);
        result = 31 * result + (unitName != null ? unitName.hashCode() : 0);
        result = 31 * result + (rmaName != null ? rmaName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (rmaDayesss != null ? rmaDayesss.hashCode() : 0);
        result = 31 * result + (rmaDayes != null ? rmaDayes.hashCode() : 0);
        result = 31 * result + (supplyerList != null ? supplyerList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CurrentProduct{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", description='" + description + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", brandId='" + brandId + '\'' +
                ", catagoryId='" + catagoryId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", pursesPrice='" + pursesPrice + '\'' +
                ", sellPrice='" + sellPrice + '\'' +
                ", rmaId='" + rmaId + '\'' +
                ", userId='" + userId + '\'' +
                ", date='" + date + '\'' +
                ", warrentyVoidDate='" + warrentyVoidDate + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", catagoryName='" + catagoryName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", rmaName='" + rmaName + '\'' +
                ", userName='" + userName + '\'' +
                ", rmaDayesss='" + rmaDayesss + '\'' +
                ", rmaDayes='" + rmaDayes + '\'' +
                ", supplyerList='" + supplyerList + '\'' +
                '}';
    }
}
