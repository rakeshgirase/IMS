package com.exuberant.ims.list;
public class ListBrands {
    public String id;
    public String brandName;
    public String brandComment;
    public String supplyerName;
    public String creatorId;
    public String date;
    public ListBrands(String id, String brandName, String brandComment, String supplyerName, String creatorId, String date) {
        this.id = id;
        this.brandName = brandName;
        this.brandComment = brandComment;
        this.supplyerName = supplyerName;
        this.creatorId = creatorId;
        this.date = date;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getBrandName() {
        return this.brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getBrandComment() {
        return this.brandComment;
    }
    public void setBrandComment(String brandComment) {
        this.brandComment = brandComment;
    }
    public String getSupplyerName() {
        return this.supplyerName;
    }
    public void setSupplyerName(String supplyerName) {
        this.supplyerName = supplyerName;
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
