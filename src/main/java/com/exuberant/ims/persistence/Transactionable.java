package com.exuberant.ims.persistence;


public interface Transactionable {
    void runInTransaction(UnitOfWork unitOfWork);

    void runWithSession(UnitOfWork unitOfWork);

    <V> V fetch(DBWorker<V> worker);
}
