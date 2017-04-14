package com.exuberant.ims.gateway;

import java.io.Serializable;
import java.util.List;

public class HibernateRepository {

    private static final HibernateRepository INSTANCE = new HibernateRepository();

    private HibernateRepository() {
    }

    public static HibernateRepository getRepository() {
        return INSTANCE;
    }


    public <E> E save(E e) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        transactionable.runWithSession(session -> session.save(e));
        return e;
    }

    public <E> E get(Class<E> entityType, Serializable id) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        return transactionable.fetch(session -> session.get(entityType, id));
    }

    public <E> List<E> getAll(Class<E> entityType) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        return transactionable.fetch(session -> session.createCriteria(entityType).list());
    }

    public <E> void delete(E e) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        transactionable.runWithSession(session -> session.delete(e));
    }

    public <E> void update(E e) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        transactionable.runWithSession(session -> session.update(e));
    }

    public <E> void saveOrUpdate(E e) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        transactionable.runWithSession(session -> session.saveOrUpdate(e));
    }

}
