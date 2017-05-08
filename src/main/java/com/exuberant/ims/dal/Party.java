package com.exuberant.ims.dal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Party {
    @Id
    private Long id;
    private String name;
    private String address;
    @OneToMany
    private Collection<Contact> contactNumbers;
}
