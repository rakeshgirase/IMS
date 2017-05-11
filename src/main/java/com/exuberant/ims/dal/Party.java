package com.exuberant.ims.dal;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "PARTY_SEQ", allocationSize = 25)
    private Long id;
    private String name;
    private String address;
    @OneToMany
    private Collection<Contact> contactNumbers;
}
