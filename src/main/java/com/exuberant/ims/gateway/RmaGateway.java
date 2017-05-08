package com.exuberant.ims.gateway;

import com.exuberant.ims.dal.RMA;
import com.exuberant.ims.persistence.HibernateRepository;

public class RmaGateway {

    public void save(RMA rma) {
        HibernateRepository.getRepository().save(rma);
    }

    public void view(RMA rma) {
    }

    public void selectedView(RMA rma) {
    }

    public void searchView(RMA rma) {
    }

    public void update(RMA rma) {
        HibernateRepository.getRepository().update(rma);
    }

    public void delete(RMA rma) {
        HibernateRepository.getRepository().update(rma);
    }

    public boolean isUniqName(RMA rma) {
        boolean uniqRMA = true;
        return uniqRMA;
    }

    public boolean isNotUse(RMA rma) {
        boolean isNotUse = true;
        return isNotUse;
    }
}
