package com.exuberant.ims.persistence;

import org.hibernate.Session;

@FunctionalInterface
public interface UnitOfWork {
    void work(Session session);
}
