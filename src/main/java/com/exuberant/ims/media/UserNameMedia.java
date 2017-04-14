package com.exuberant.ims.media;

import com.exuberant.ims.dal.Users;

public class UserNameMedia {
    Long id;
    Users users;

    public UserNameMedia() {
    }

    public UserNameMedia(Long Id) {
        this.id = Id;
    }

    public UserNameMedia(Long id, Users users) {
        this.id = id;
        this.users = users;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}