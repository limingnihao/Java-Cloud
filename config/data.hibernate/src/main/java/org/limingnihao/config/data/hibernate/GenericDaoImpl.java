package org.limingnihao.config.data.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("unchecked")
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public GenericDaoImpl() {
        clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void deleteEntity(T entity) {
        PersistenceEntity e = (PersistenceEntity) entity;
        e.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        e.setHdelete(1);
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteEntityReal(T entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void recoverEntity(T entity) {
        PersistenceEntity e = (PersistenceEntity) entity;
        e.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        e.setHdelete(0);
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public T getEntity(PK id) {
        if (id != null) {
            return (T) this.sessionFactory.getCurrentSession().get(clazz, id);
        } else {
            return null;
        }
    }

    @Override
    public List<T> getList() {
        return this.sessionFactory.getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
    }

    @Override
    public List<T> getList(int firstResult, int maxResults) {
        Query<T> query = this.sessionFactory.getCurrentSession().createQuery("from " + clazz.getName());
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public int getList_count() {
        Query<Long> query = this.sessionFactory.getCurrentSession().createQuery("select count(entity) from " + clazz.getName() + " entity");
        Long count = query.getSingleResult();
        return count.intValue();
    }

    //	@Override
    //	public void saveEntity(T entity) {
    //		this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
    //		this.sessionFactory.getCurrentSession().flush();
    //    }


    @Override
    public void createEntity(PersistenceEntity entity) {
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        this.sessionFactory.getCurrentSession().persist(entity);
        //        this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void createEntitys(PersistenceEntity[] entitys) {
        for (PersistenceEntity entity : entitys) {
            entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            this.sessionFactory.getCurrentSession().persist(entity);
        }
        //        this.sessionFactoary.getCurrentSession().flush();
    }

    @Override
    public void updateEntity(PersistenceEntity entity) throws StaleStateException {
        //        this.sessionFactory.getCurrentSession().refresh(entity);

        entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
        //        this.sessionFactory.openSession().merge(entity);
        //        this.sessionFactory.getCurrentSession().merge(entity);
        //        this.sessionFactory.getCurrentSession().update(entity);
        this.sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateEntitys(PersistenceEntity[] entitys) {
        for (PersistenceEntity entity : entitys) {
            entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //            this.sessionFactory.getCurrentSession().update(entity);
        }
        //        this.sessionFactory.getCurrentSession().flush();
    }

}
