package com.exuberant.ims.gateway;

import java.io.Serializable;
import java.util.List;

public interface Repository {

    <E> E save(E e);

    <E> E get(Class<E> entityType, Serializable id);

    <E> List<E> getAll(Class<E> entityType);

    <E> void delete(E e);

    <E> void update(E e);

    <E> void saveOrUpdate(E e);
}
