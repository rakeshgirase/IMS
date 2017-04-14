package com.exuberant.ims.list;

public class ListCustomer {
    public String id;
    public String customerName;
    public String customerContNo;
    public String customerAddress;
    public String totalBuy;
    public String addBy;
    public String addedDate;

    public ListCustomer(String id, String customerName, String customerContNo, String customerAddress, String totalBuy, String addBy, String addedDate) {
        this.id = id;
        this.customerName = customerName;
        this.customerContNo = customerContNo;
        this.customerAddress = customerAddress;
        this.totalBuy = totalBuy;
        this.addBy = addBy;
        this.addedDate = addedDate;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContNo() {
        return this.customerContNo;
    }

    public void setCustomerContNo(String customerContNo) {
        this.customerContNo = customerContNo;
    }

    public String getCustomerAddress() {
        return this.customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getTotalBuy() {
        return this.totalBuy;
    }

    public void setTotalBuy(String totalBuy) {
        this.totalBuy = totalBuy;
    }

    public String getAddBy() {
        return this.addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public String getAddedDate() {
        return this.addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }
}
