package com.exuberant.ims.media;
public class UserNameMedia {
    String id;
    String userName;
    public UserNameMedia() {
    }
    public UserNameMedia(String Id) {
        this.id = Id;
    }
    public UserNameMedia(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}