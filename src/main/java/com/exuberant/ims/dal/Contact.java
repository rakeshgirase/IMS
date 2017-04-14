package com.exuberant.ims.dal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contact {
    @Id
    private String number;
    private String type;

}
