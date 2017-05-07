package com.exuberant.ims.media;

import com.exuberant.ims.dal.User;

public class UserNameMedia {
    Long id;
    User user;

    public UserNameMedia() {
    }

    public UserNameMedia(Long Id) {
        this.id = Id;
    }

    public UserNameMedia(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}