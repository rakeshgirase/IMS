package com.exuberant.ims.persistence;

import org.hibernate.Session;

public interface DBWorker<V> {
    V work(Session session);
}
