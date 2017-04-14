package com.exuberant.ims.dal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Weight {

    @Id
    private Long id;
    private Long weight;
    private Unit unit;

}
