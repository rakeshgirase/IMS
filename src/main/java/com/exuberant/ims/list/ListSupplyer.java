package com.exuberant.ims.list;

public class ListSupplyer {
    public String supplyerId;
    public String supplyerName;
    public String supplyerPhoneNumber;
    public String supplyerAddress;
    public String supplyerDescription;
    public String creatorId;
    public String dataOfjoining;

    public ListSupplyer(String supplyerId, String supplyerName) {
        this.supplyerId = supplyerId;
        this.supplyerName = supplyerName;
    }

    public ListSupplyer(String supplyerId, String supplyerName, String supplyerPhoneNumber, String supplyerAddress, String supplyerDescription, String dataOfjoining) {
        this.supplyerId = supplyerId;
        this.supplyerName = supplyerName;
        this.supplyerPhoneNumber = supplyerPhoneNumber;
        this.supplyerAddress = supplyerAddress;
        this.supplyerDescription = supplyerDescription;
        this.dataOfjoining = dataOfjoining;
    }

    public String getSupplyerPhoneNumber() {
        return this.supplyerPhoneNumber;
    }

    public String getSupplyerAddress() {
        return this.supplyerAddress;
    }

    public void setSupplyerAddress(String supplyerAddress) {
        this.supplyerAddress = supplyerAddress;
    }

    public String getSupplyerDescription() {
        return this.supplyerDescription;
    }

    public String getDataOfjoining() {
        return this.dataOfjoining;
    }

    public String getSupplyerId() {
        return this.supplyerId;
    }

    public void setSupplyerId(String supplyerId) {
        this.supplyerId = supplyerId;
    }

    public String getSupplyerName() {
        return this.supplyerName;
    }

    public void setSupplyerName(String supplyerName) {
        this.supplyerName = supplyerName;
    }
}
