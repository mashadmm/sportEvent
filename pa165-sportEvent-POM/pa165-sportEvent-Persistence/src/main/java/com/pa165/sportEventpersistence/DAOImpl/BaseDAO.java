/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpersistence.DAOImpl;


import com.pa165.sportEventpersistence.DAO.IBaseDAO;
import com.pa165.sportEventpersistence.Exceptions.IllegalArgumentPersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author Maria
 */

  public abstract class BaseDAO <T> implements IBaseDAO <T>{

    private Class<T> entityClass;
        
    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    protected abstract boolean validateEntity(T entity) throws IllegalArgumentPersistenceException;
    
   public T persist(T entity) throws IllegalArgumentPersistenceException{
        if(this.validateEntity(entity)){
        EntityManager em = getEntityManager();
        getEntityManager().persist(entity);
        return entity;
        }
        return null;
    }

    public T edit(T entity) throws IllegalArgumentPersistenceException {
         if(this.validateEntity(entity)){
            getEntityManager().merge(entity);
            return entity;
         }
         return null;
    }

    public void remove(T entity) throws IllegalArgumentPersistenceException{
       if(this.validateEntity(entity)){
        getEntityManager().remove(entity);
        }
    }

    public List<T> findAll() {

    Query q = getEntityManager().createQuery("SELECT e FROM " + entityClass.getName()
                + " e");
        List<T> list = (List<T>) q.getResultList();
        return list;
    }

    public T findById(Long id)  throws IllegalArgumentPersistenceException{
        if (this.validateID(id)){
        T e = getEntityManager().find(entityClass, id);
        return e;
        }
        return null;
    }
    
    public boolean validateID(Long id)  throws IllegalArgumentPersistenceException{
        
        if ((id == null)|| (id < 0)) {
            throw new IllegalArgumentPersistenceException("Method BaseDAO.validateId call with Null argument");
        }
        
        return true;
    }
   

}
 

