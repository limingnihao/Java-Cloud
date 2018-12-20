package org.limingnihao.framework.mybatis;

import java.util.List;

public interface BaseDao<E, T> {

    void create(E e);

    void update(E e);

    void delete(T id);

    E getEntityById(T id);

    List<E> getEntityList();

}
