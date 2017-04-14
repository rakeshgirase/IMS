package com.exuberant.ims.list;

public class ListRma {
    public String ramId;
    public String rmaName;
    public String rmaDays;
    public String rmaComment;
    public String creatorName;
    public String date;

    public ListRma(String ramId, String rmaName, String rmaDays, String rmaComment, String creatorName, String date) {
        this.ramId = ramId;
        this.rmaName = rmaName;
        this.rmaDays = rmaDays;
        this.rmaComment = rmaComment;
        this.creatorName = creatorName;
        this.date = date;
    }

    public String getRamId() {
        return this.ramId;
    }

    public void setRamId(String ramId) {
        this.ramId = ramId;
    }

    public String getRmaName() {
        return this.rmaName;
    }

    public void setRmaName(String rmaName) {
        this.rmaName = rmaName;
    }

    public String getRmaDays() {
        return this.rmaDays;
    }

    public void setRmaDays(String rmaDays) {
        this.rmaDays = rmaDays;
    }

    public String getRmaComment() {
        return this.rmaComment;
    }

    public void setRmaComment(String rmaComment) {
        this.rmaComment = rmaComment;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
