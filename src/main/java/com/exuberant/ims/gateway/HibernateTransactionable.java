package com.exuberant.ims.gateway;


import org.hibernate.Session;

public class HibernateTransactionable implements Transactionable {

    private static HibernateTransactionable singleton = new HibernateTransactionable();
    private static ThreadLocal<Session> threadLocal = new ThreadLocal<>();

    private HibernateTransactionable() {
    }

    public static HibernateTransactionable getHibernateTransactionable() {
        return singleton;
    }

    public void runInTransaction(UnitOfWork unitOfWork){
        Session session = getSession();
        session.beginTransaction();
        unitOfWork.work(session);
        session.getTransaction().commit();
    }

    @Override
    public void runWithSession(UnitOfWork unitOfWork) {
        Session session = getSession();
        threadLocal.set(session);
        unitOfWork.work(session);
        session.close();
    }

    @Override
    public <V> V fetch(DBWorker<V> worker) {
        Session session = getSession();
        V v = worker.work(session);
        session.close();
        return v;
    }

    private Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
