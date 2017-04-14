package com.exuberant.ims.list;

public class ListCatagory {
    public String id;
    public String catagoryName;
    public String catagoryDescription;
    public String brandId;
    public String supplyerId;
    public String creatorId;
    public String date;

    public ListCatagory(String id, String catagoryName, String catagoryDescription, String brandId, String supplyerId, String creatorId, String date) {
        this.id = id;
        this.catagoryName = catagoryName;
        this.catagoryDescription = catagoryDescription;
        this.brandId = brandId;
        this.supplyerId = supplyerId;
        this.creatorId = creatorId;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatagoryName() {
        return this.catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getCatagoryDescription() {
        return this.catagoryDescription;
    }

    public void setCatagoryDescription(String catagoryDescription) {
        this.catagoryDescription = catagoryDescription;
    }

    public String getBrandId() {
        return this.brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getSupplyerId() {
        return this.supplyerId;
    }

    public void setSupplyerId(String supplyerId) {
        this.supplyerId = supplyerId;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
