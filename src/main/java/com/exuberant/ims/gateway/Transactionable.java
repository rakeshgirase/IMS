package com.exuberant.ims.gateway;


public interface Transactionable {
    void runInTransaction(UnitOfWork unitOfWork);
    void runWithSession(UnitOfWork unitOfWork);
    <V> V fetch(DBWorker<V> worker);
}
