package com.exuberant.ims.dal;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class Party {
    @Id
    private Long id;
    private String name;
    private String address;
    private Collection<Contact> contactNumbers;
}
