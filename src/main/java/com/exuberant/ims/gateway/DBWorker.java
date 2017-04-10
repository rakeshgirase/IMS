package com.exuberant.ims.gateway;

import org.hibernate.Session;

public interface DBWorker<V> {
    V work(Session session);
}
