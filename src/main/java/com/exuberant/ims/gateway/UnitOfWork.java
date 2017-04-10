package com.exuberant.ims.gateway;

import org.hibernate.Session;

@FunctionalInterface
public interface UnitOfWork {
   void work(Session session);
}
