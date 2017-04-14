package com.exuberant.ims.dal;

import javax.persistence.Entity;

@Entity
public class Brand {
    public String id;
    public String brandName;
    public String brandComment;
    public String supplierId;
    public Long creatorId;
    public String date;
    public String supplierName;
    public String creatorName;
}
