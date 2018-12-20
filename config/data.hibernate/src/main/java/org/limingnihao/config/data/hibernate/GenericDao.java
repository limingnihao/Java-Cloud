package org.limingnihao.config.data.hibernate;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public interface GenericDao<T, PK extends Serializable> {

    void deleteEntity(T entity);

    void deleteEntityReal(T entity);

    void recoverEntity(T entity);

    T getEntity(PK id);

    List<T> getList();

    List<T> getList(int firstResult, int maxResults);

    int getList_count();

    //	void saveEntity(T entity);

    void createEntity(PersistenceEntity entity);

    void createEntitys(PersistenceEntity entitys[]);


    void updateEntity(PersistenceEntity entity);

    void updateEntitys(PersistenceEntity entitys[]);


}