package com.exuberant.ims.list;
public class ListPreSell {
    String Id;
    String productID;
    String customerID;
    String pursesPrice;
    String sellPrice;
    String oldQuantity;
    String quantity;
    String totalPrice;
    String pursrsDate;
    String warrentyVoidDate;
    String sellerID;
    String sellDate;
    public ListPreSell(String Id, String productID, String customerID, String pursesPrice, String sellPrice, String oldQuantity, String quantity, String totalPrice, String pursrsDate, String warrentyVoidDate, String sellerID, String sellDate) {
        this.Id = Id;
        this.productID = productID;
        this.customerID = customerID;
        this.pursesPrice = pursesPrice;
        this.sellPrice = sellPrice;
        this.oldQuantity = oldQuantity;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.pursrsDate = pursrsDate;
        this.warrentyVoidDate = warrentyVoidDate;
        this.sellerID = sellerID;
        this.sellDate = sellDate;
    }
    public String getId() {
        return this.Id;
    }
    public void setId(String Id) {
        this.Id = Id;
    }
    public String getProductID() {
        return this.productID;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public String getCustomerID() {
        return this.customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public String getPursesPrice() {
        return this.pursesPrice;
    }
    public void setPursesPrice(String pursesPrice) {
        this.pursesPrice = pursesPrice;
    }
    public String getSellPrice() {
        return this.sellPrice;
    }
    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }
    public String getOldQuantity() {
        return this.oldQuantity;
    }
    public void setOldQuantity(String oldQuantity) {
        this.oldQuantity = oldQuantity;
    }
    public String getQuantity() {
        return this.quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getPursrsDate() {
        return this.pursrsDate;
    }
    public void setPursrsDate(String pursrsDate) {
        this.pursrsDate = pursrsDate;
    }
    public String getWarrentyVoidDate() {
        return this.warrentyVoidDate;
    }
    public void setWarrentyVoidDate(String warrentyVoidDate) {
        this.warrentyVoidDate = warrentyVoidDate;
    }
    public String getSellerID() {
        return this.sellerID;
    }
    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }
    public String getSellDate() {
        return this.sellDate;
    }
    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }
}
