package com.exuberant.ims.gateway;

import java.io.Serializable;
import java.util.List;

public class HibernateRepository implements Repository{

    private static final Repository INSTANCE = new HibernateRepository();

    private HibernateRepository() {
    }

    public static Repository getRepository() {
        return INSTANCE;
    }


    @Override
    public <E> E save(E e) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        transactionable.runWithSession(session -> session.save(e));
        return e;
    }

    @Override
    public <E> E get(Class<E> entityType, Serializable id) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        return transactionable.fetch(session -> session.get(entityType, id));
    }

    @Override
    public <E> List<E> getAll(Class<E> entityType) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        return transactionable.fetch(session -> session.createCriteria(entityType).list());
    }

    @Override
    public <E> void delete(E e) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        transactionable.runWithSession(session -> session.delete(e));
    }

    @Override
    public <E> void update(E e) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        transactionable.runWithSession(session -> session.update(e));
    }

    @Override
    public <E> void saveOrUpdate(E e) {
        Transactionable transactionable = HibernateTransactionable.getHibernateTransactionable();
        transactionable.runWithSession(session -> session.saveOrUpdate(e));
    }

}
